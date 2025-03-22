package com.parkandride.park_and_ride_mvp.service;

import com.parkandride.park_and_ride_mvp.entity.ParkingSlot;
import com.parkandride.park_and_ride_mvp.entity.SlotStatus;
import com.parkandride.park_and_ride_mvp.entity.User;
import com.parkandride.park_and_ride_mvp.repository.ParkingSlotRepository;
import com.parkandride.park_and_ride_mvp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    private ParkingSlotRepository slotRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ParkingSlotWebSocketHandler webSocketHandler;

    // ✅ Get available slots at a station
    public List<ParkingSlot> getAvailableSlots(String station) {
        return slotRepo.findByStationAndStatus(station, SlotStatus.AVAILABLE);
    }

    // ✅ Assign an available slot to a user & store their vehicle number
    @Transactional
    public ParkingSlot bookSlot(Long slotId, Long userId, String licensePlate) throws Exception {
        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new Exception("Slot not found!"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new Exception("User not found!"));

        if (slot.getStatus() != SlotStatus.AVAILABLE) {
            throw new Exception("Slot already booked!");
        }

        slot.setStatus(SlotStatus.RESERVED);
        slot.setBookedBy(user);
        slot.setLicensePlate(licensePlate); // ✅ Store vehicle number for LPR tracking
        slotRepo.save(slot);

        // ✅ Send real-time update
        webSocketHandler.broadcastUpdate("Slot " + slot.getSlotNumber() + " has been RESERVED for " + licensePlate + ".");

        return slot;
    }

    // ✅ New: Automatically mark slot as occupied when vehicle enters (via LPR)
    @Transactional
    public boolean assignParkingSlot(String licensePlate) throws Exception {
        Optional<ParkingSlot> optionalSlot = slotRepo.findByLicensePlate(licensePlate);

        if (optionalSlot.isPresent()) {
            ParkingSlot slot = optionalSlot.get();

            if (slot.getStatus() == SlotStatus.RESERVED) {
                slot.setStatus(SlotStatus.OCCUPIED);
                slotRepo.save(slot);

                // ✅ Send WebSocket update for LPR event
                webSocketHandler.broadcastLPRUpdate(licensePlate, "OCCUPIED");
                return true;
            } else {
                throw new Exception("Slot must be RESERVED before marking as OCCUPIED.");
            }
        }

        return false; // No matching booking found for this license plate
    }

    // ✅ Mark slot as occupied upon manual check-in
    @Transactional
    public void markSlotAsOccupied(Long slotId) throws Exception {
        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new Exception("Slot not found!"));

        if (slot.getStatus() != SlotStatus.RESERVED) {
            throw new Exception("Slot must be reserved before occupying.");
        }

        slot.setStatus(SlotStatus.OCCUPIED);
        slotRepo.save(slot);

        // ✅ Send real-time update
        webSocketHandler.broadcastUpdate("Slot " + slot.getSlotNumber() + " is now OCCUPIED.");
    }

    // ✅ Release the slot when the user cancels
    @Transactional
    public void cancelBooking(Long slotId) throws Exception {
        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new Exception("Slot not found!"));

        slot.setStatus(SlotStatus.AVAILABLE);
        slot.setBookedBy(null);
        slot.setLicensePlate(null); // ✅ Clear stored vehicle number
        slotRepo.save(slot);

        // ✅ Send real-time update
        webSocketHandler.broadcastUpdate("Slot " + slot.getSlotNumber() + " is now AVAILABLE.");
    }
}
