package com.parkandride.park_and_ride_mvp.configure;

import com.parkandride.park_and_ride_mvp.service.ParkingSlotWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ParkingSlotWebSocketHandler parkingSlotWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(parkingSlotWebSocketHandler, "/parking-updates")
                .setAllowedOrigins("*")  // ✅ Allow WebSocket connections from any origin
                .setAllowedOriginPatterns("*") ;// ✅ Allow different origins without CORS issues
                //.withSockJS(); // ❌ REMOVE this line if using Postman/WebSocket clients directly
    }
}
