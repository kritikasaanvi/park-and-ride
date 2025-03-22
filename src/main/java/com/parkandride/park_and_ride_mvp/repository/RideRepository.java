package com.parkandride.park_and_ride_mvp.repository;



import com.parkandride.park_and_ride_mvp.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
