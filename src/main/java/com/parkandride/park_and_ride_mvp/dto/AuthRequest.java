package com.parkandride.park_and_ride_mvp.dto;


import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String email;
    private String password;

    // Getters and Setters
}
