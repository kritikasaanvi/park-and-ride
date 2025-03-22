package com.parkandride.park_and_ride_mvp.dto;



public class RideResponse {
    public String message;
    public Long rideId;
    public String status;

    public RideResponse(String message, Long rideId, String status) {
        this.message = message;
        this.rideId = rideId;
        this.status = status;
    }
}
