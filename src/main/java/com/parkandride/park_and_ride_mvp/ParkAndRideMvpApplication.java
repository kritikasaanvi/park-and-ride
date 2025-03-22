package com.parkandride.park_and_ride_mvp;

import com.parkandride.park_and_ride_mvp.configure.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class ParkAndRideMvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkAndRideMvpApplication.class, args);
	}

}
