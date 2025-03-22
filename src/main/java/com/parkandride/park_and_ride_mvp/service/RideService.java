// service/RideService.java
package com.parkandride.park_and_ride_mvp.service;

import com.parkandride.park_and_ride_mvp.dto.RideRequest;
import com.parkandride.park_and_ride_mvp.dto.RideResponse;
import com.parkandride.park_and_ride_mvp.entity.Ride;
import com.parkandride.park_and_ride_mvp.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public RideResponse bookRide(RideRequest request) {
        Ride ride = new Ride();
        ride.setUserId(request.userId);
        ride.setPickupLocation(request.pickupLocation);
        ride.setDropLocation(request.dropLocation);
        ride.setRideType(request.rideType);
        ride.setScheduledTime(request.scheduledTime);
        ride.setShared(request.isShared);

        ride.setStatus("BOOKED");

        Ride savedRide = rideRepository.save(ride);

        return new RideResponse("Ride booked successfully", savedRide.getId(), savedRide.getStatus());
    }
}
