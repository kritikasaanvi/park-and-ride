package com.parkandride.park_and_ride_mvp.controller;

import com.parkandride.park_and_ride_mvp.dto.BookingRequest;
import com.parkandride.park_and_ride_mvp.dto.StationDTO;
import com.parkandride.park_and_ride_mvp.entity.ParkingSlot;
import com.parkandride.park_and_ride_mvp.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    // ✅ Get all available slots at a station
    @GetMapping("/available")
    public ResponseEntity<List<ParkingSlot>> getAvailable(@RequestParam String station) {
        return ResponseEntity.ok(parkingService.getAvailableSlots(station));
    }

    // ✅ Book a parking slot with license plate
    @PostMapping("/book")
    public ResponseEntity<?> bookSlot(@RequestBody BookingRequest request) {
        try {
            ParkingSlot slot = parkingService.bookSlot(
                    request.getSlotId(),
                    request.getUserId(),
                    request.getLicensePlate() // ✅ Pass vehicle number for LPR
            );
            return ResponseEntity.ok(slot);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Get list of available stations (mocked for now)
    @GetMapping("/stations")
    public ResponseEntity<List<StationDTO>> getStations() {
        List<StationDTO> stations = List.of(
                new StationDTO(1L, "Metro Station A"),
                new StationDTO(2L, "Metro Station B"),
                new StationDTO(3L, "Metro Station C")
        );
        return ResponseEntity.ok(stations);
    }

    // Cancel a parking slot booking
    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestParam Long slotId) {
        try {
            parkingService.cancelBooking(slotId);
            return ResponseEntity.ok("Cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mark a slot as occupied (Check-in)
    @PostMapping("/occupy/{slotId}")
    public ResponseEntity<?> markSlotAsOccupied(@PathVariable Long slotId) {
        try {
            parkingService.markSlotAsOccupied(slotId);
            return ResponseEntity.ok("Slot marked as occupied.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
