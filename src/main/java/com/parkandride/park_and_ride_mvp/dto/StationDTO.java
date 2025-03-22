package com.parkandride.park_and_ride_mvp.dto;

public class StationDTO {
    private Long id;
    private String name;

    public StationDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
