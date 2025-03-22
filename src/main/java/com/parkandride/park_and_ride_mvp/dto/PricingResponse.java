package com.parkandride.park_and_ride_mvp.dto;

public class PricingResponse {
    private double finalPrice;
    private String message;

    public PricingResponse(double finalPrice, String message) {
        this.finalPrice = finalPrice;
        this.message = message;
    }

    // Getters and setters
}
