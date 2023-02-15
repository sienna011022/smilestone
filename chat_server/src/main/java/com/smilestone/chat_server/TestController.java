package com.smilestone.chat_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final RabbitTemplate rabbitTemplate;

    private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
    private final static String CHAT_QUEUE_NAME = "chat.queue";

    @MessageMapping("chat.enter.{chatRoomId}")
    public void sendEnterMessage(ChatMessage message, @DestinationVariable String chatRoomId) {
        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, "chatroom." + chatRoomId, message);
    }

    @MessageMapping("chat.message.{chatRoomId}")
    public void sendMessage(ChatMessage message, @DestinationVariable String chatRoomId) {
        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, "chatroom." + chatRoomId, message);
    }

    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(ChatMessage message){
        System.out.println("received : " + message.getMessage());
    }
}
