package com.smilestone.chat_server;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final SimpMessagingTemplate template;

    @MessageMapping("chat.{chatRoomId}")
    public void sendEnterMessage(ChatMessage message, @DestinationVariable String chatRoomId) {
        template.convertAndSend("/chat/" + chatRoomId, message);
    }
}
