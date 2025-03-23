
package com.parkandride.park_and_ride_mvp.controller;

import com.parkandride.park_and_ride_mvp.dto.PricingResponse;
import com.parkandride.park_and_ride_mvp.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @GetMapping("/calculate")
    public ResponseEntity<PricingResponse> calculatePrice(@RequestParam int durationInHours) {
        // ✅ This must return a full JSON response like { price: 30.0, durationInHours: 2 }
        PricingResponse response = pricingService.calculateDynamicParkingPrice(durationInHours);
        return ResponseEntity.ok(response);
    }
}
