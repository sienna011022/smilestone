package com.smilestone.chat_server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("chat.{chatRoomId}")
    public void sendEnterMessage(ChatMessage message, @DestinationVariable String chatRoomId) throws JsonProcessingException {
        template.convertAndSend("/chat/" + chatRoomId, objectMapper.writeValueAsString(message));
    }
}
