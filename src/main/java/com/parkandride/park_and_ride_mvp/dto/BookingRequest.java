package com.parkandride.park_and_ride_mvp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private Long slotId;
    private Long userId;
    private String licensePlate; // ✅ New field for LPR tracking
}
