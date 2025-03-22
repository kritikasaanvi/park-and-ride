package com.parkandride.park_and_ride_mvp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String pickupLocation;
    private String dropLocation;

    @Enumerated(EnumType.STRING)
    private RideType rideType;

    private LocalDateTime scheduledTime;
    private boolean isShared;
    private String status;
}
