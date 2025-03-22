package com.parkandride.park_and_ride_mvp.repository;

import com.parkandride.park_and_ride_mvp.entity.PricingRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
}
