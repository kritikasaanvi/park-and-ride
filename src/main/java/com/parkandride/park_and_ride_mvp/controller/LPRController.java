package com.parkandride.park_and_ride_mvp.controller;



import com.parkandride.park_and_ride_mvp.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lpr")
public class LPRController {

    private final ParkingService parkingService;

    public LPRController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // ✅ Simulated License Plate Recognition API
    @PostMapping("/recognize")
    public ResponseEntity<String> processLicensePlate(@RequestParam("licensePlate") String licensePlate) {
        try {
            boolean slotUpdated = parkingService.assignParkingSlot(licensePlate);
            return slotUpdated
                    ? ResponseEntity.ok("Parking slot assigned for license plate: " + licensePlate)
                    : ResponseEntity.badRequest().body("No matching booking found for plate: " + licensePlate);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing license plate: " + e.getMessage());
        }
    }
}
