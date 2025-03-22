package com.parkandride.park_and_ride_mvp.controller;

// controller/DBTestController.java


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dbtest")
public class DBTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> testDBConnection() {
        return jdbcTemplate.queryForList("SHOW TABLES");
    }
}
