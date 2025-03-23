
package com.parkandride.park_and_ride_mvp.repository;

import com.parkandride.park_and_ride_mvp.entity.ParkingSlot;
import com.parkandride.park_and_ride_mvp.entity.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    // ✅ Find available slots at a specific station
    List<ParkingSlot> findByStationAndStatus(String station, SlotStatus status);

    // ✅ Find a slot by its license plate for LPR-based slot tracking
    Optional<ParkingSlot> findByLicensePlate(String licensePlate);

    // ✅ FIXED: Count by correct field name 'status' (not 'slotStatus')
    long countByStatus(SlotStatus status);
}
