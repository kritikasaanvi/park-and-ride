
package com.parkandride.park_and_ride_mvp.service;

import com.parkandride.park_and_ride_mvp.dto.PricingResponse;
import com.parkandride.park_and_ride_mvp.entity.PricingRule;
import com.parkandride.park_and_ride_mvp.entity.SlotStatus;
import com.parkandride.park_and_ride_mvp.repository.ParkingSlotRepository;
import com.parkandride.park_and_ride_mvp.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class PricingService {

    @Autowired
    private PricingRuleRepository pricingRuleRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    public PricingResponse calculateDynamicParkingPrice(int durationInHours) {
        PricingRule rule = pricingRuleRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Pricing rule not found"));

        LocalTime now = LocalTime.now();
        boolean isPeak = now.isAfter(rule.getPeakStart()) && now.isBefore(rule.getPeakEnd());

        long totalSlots = parkingSlotRepository.count();
        long occupied = parkingSlotRepository.countByStatus(SlotStatus.OCCUPIED);
        double occupancyRate = totalSlots > 0 ? (double) occupied / totalSlots : 0;

        double price = rule.getBaseRate() * durationInHours;
        StringBuilder msg = new StringBuilder("Base: ₹" + price);

        if (isPeak) {
            price *= rule.getPeakMultiplier();
            msg.append(" | Peak time surcharge");
        } else {
            price *= (1 - rule.getOffPeakDiscount());
            msg.append(" | Off-peak discount");
        }

        price *= (1 + rule.getOccupancyMultiplier() * occupancyRate);
        msg.append(" | Adjusted for occupancy: ").append(Math.round(occupancyRate * 100)).append("%");

        // ✅ FIX: Pass durationInHours as second argument
        return new PricingResponse(
                Math.round(price * 100.0) / 100.0,
                durationInHours,
                msg.toString()
        );
    }
}
