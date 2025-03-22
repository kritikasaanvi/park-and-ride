package com.parkandride.park_and_ride_mvp.dto;



import com.parkandride.park_and_ride_mvp.entity.RideType;
import java.time.LocalDateTime;

public class RideRequest {
    public String userId;
    public String pickupLocation;
    public String dropLocation;
    public RideType rideType;
    public LocalDateTime scheduledTime;
    public boolean isShared;
}
