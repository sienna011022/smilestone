package com.smilestone.chat_server;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class MyWebSocketHandler extends TextWebSocketHandler {
    ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());

    }

    private void sendMessage(WebSocketSession session, String payload) {
        TextMessage message = new TextMessage(payload);
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String payload) {
        sessionMap.forEach((s, webSocketSession)-> this.sendMessage(webSocketSession, payload));
    }
}
