// controller/RideController.java
package com.parkandride.park_and_ride_mvp.controller;

import com.parkandride.park_and_ride_mvp.dto.RideRequest;
import com.parkandride.park_and_ride_mvp.dto.RideResponse;
import com.parkandride.park_and_ride_mvp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/book")
    public RideResponse bookRide(@RequestBody RideRequest rideRequest) {
        return rideService.bookRide(rideRequest);
    }
}
