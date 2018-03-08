package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //使用stomp订阅，接收消息之前需要先连接到端点url如"ws://localhost:8080/agent"
        registry.addEndpoint("/agent").withSockJS().setInterceptors(new HttpHandshakeInterceptor());

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //消息代理前缀
        registry.enableSimpleBroker("/user");   // Enables a simple in-memory broker
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new UserInterceptor());
    }
}
