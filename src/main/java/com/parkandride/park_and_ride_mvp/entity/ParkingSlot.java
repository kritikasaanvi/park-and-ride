package com.parkandride.park_and_ride_mvp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String station;

    @Column(nullable = false, unique = true)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus status = SlotStatus.AVAILABLE; // Enum: AVAILABLE, RESERVED, OCCUPIED

    @ManyToOne
    @JoinColumn(name = "booked_by", nullable = true)
    private User bookedBy; // Can be null if not assigned

    // ✅ NEW FIELD: Store license plate for LPR integration
    @Column(nullable = true, unique = true)
    private String licensePlate;
}
