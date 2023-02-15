package com.smilestone.chat_server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/chatroom/", "/queue", "/topic", "/exchange", "/amq/queue")
                .setRelayHost("192.168.201.128")
                .setClientLogin("test")
                .setClientPasscode("test")
                .setSystemLogin("test")
                .setSystemPasscode("test")
                .setVirtualHost("/");
        registry.setApplicationDestinationPrefixes("/pub");
        registry.setPathMatcher(new AntPathMatcher("."));
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/smilestone/chat")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }


}
