package com.parkandride.park_and_ride_mvp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;

@Entity
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private double baseRate;
    private double peakMultiplier;
    private double offPeakDiscount;
    private double occupancyMultiplier;

    private LocalTime peakStart;
    private LocalTime peakEnd;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    public double getPeakMultiplier() {
        return peakMultiplier;
    }

    public void setPeakMultiplier(double peakMultiplier) {
        this.peakMultiplier = peakMultiplier;
    }

    public double getOffPeakDiscount() {
        return offPeakDiscount;
    }

    public void setOffPeakDiscount(double offPeakDiscount) {
        this.offPeakDiscount = offPeakDiscount;
    }

    public double getOccupancyMultiplier() {
        return occupancyMultiplier;
    }

    public void setOccupancyMultiplier(double occupancyMultiplier) {
        this.occupancyMultiplier = occupancyMultiplier;
    }

    public LocalTime getPeakStart() {
        return peakStart;
    }

    public void setPeakStart(LocalTime peakStart) {
        this.peakStart = peakStart;
    }

    public LocalTime getPeakEnd() {
        return peakEnd;
    }

    public void setPeakEnd(LocalTime peakEnd) {
        this.peakEnd = peakEnd;
    }
}
