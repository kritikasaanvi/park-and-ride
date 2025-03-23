
package com.parkandride.park_and_ride_mvp.dto;

public class PricingResponse {
    private double price;
    private int durationInHours;
    private String message;

    // ✅ Constructor with all 3 values
    public PricingResponse(double price, int durationInHours, String message) {
        this.price = price;
        this.durationInHours = durationInHours;
        this.message = message;
    }

    public double getPrice() {
        return price;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public String getMessage() {
        return message;
    }
}

