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
    private final ObjectMapper objectMapper;

    private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
    private final static String CHAT_QUEUE_NAME = "chat.queue";

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

//    @MessageMapping("/hello")
//    public void greeting(User user) throws Exception {
//        messageTemplate.convertAndSend("/topic/greetin", new Greeting(user.name));
//    }

    @MessageMapping("/chat")
    public void sendAllToRoom(ChatMessage message, @DestinationVariable String chatRoomId) throws Exception {
        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, chatRoomId, message);
    }

    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(ChatMessage message){
        System.out.println("received : " + message.getMessage());
    }
}
