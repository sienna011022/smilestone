package com.smilestone.chat_server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final SimpMessageSendingOperations messageTemplate;

    public TestController(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @MessageMapping("/hello")
    public void greeting(User user) throws Exception {
        messageTemplate.convertAndSend("/topic/greetin", new Greeting(user.name));
    }

    @MessageMapping("/chat")
    public void sendAllToRoom(ChatMessage message) throws Exception {
        messageTemplate.convertAndSend("/chatroom/" + message.roomId, message);
    }

    record Greeting(String hello) {}
    record User(String name) {}
    record ChatMessage(String roomId, String sender, String message, String chatAt) {}
}
