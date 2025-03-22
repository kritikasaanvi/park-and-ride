package com.parkandride.park_and_ride_mvp.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component  // ✅ This makes it a Spring Bean, so it can be Autowired in ParkingService
public class ParkingSlotWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    // ✅ Generic slot update method (Retains old functionality)
    public void broadcastUpdate(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    // ✅ New: Broadcast LPR-based Slot Updates as JSON messages
    public void broadcastLPRUpdate(String licensePlate, String status) throws IOException {
        Map<String, String> updateMessage = Map.of(
                "type", "LPR_UPDATE",
                "licensePlate", licensePlate,
                "status", status
        );

        String jsonMessage = objectMapper.writeValueAsString(updateMessage);

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(jsonMessage));
            }
        }
    }
}
