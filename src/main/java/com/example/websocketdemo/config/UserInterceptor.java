package com.example.websocketdemo.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.LinkedList;
import java.util.Map;

public class UserInterceptor extends ChannelInterceptorAdapter {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message
                    .getHeaders()
                    .get(SimpMessageHeaderAccessor.NATIVE_HEADERS);


        }

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            Object raw2 = message
                    .getHeaders()
                    .get(SimpMessageHeaderAccessor.SESSION_ID_HEADER);

            String sid = "";


            if (raw2 instanceof String) {
                Object simpSessionIdFromUserInterceptor = ((String) raw2);

                if (simpSessionIdFromUserInterceptor instanceof String) {
                    sid = (String) simpSessionIdFromUserInterceptor;
                }
            }

            Object raw = message
                    .getHeaders()
                    .get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

            if (raw instanceof Map) {
                Object agentList = ((Map) raw).get("agentList");

                if (agentList instanceof LinkedList) {
                    if (!"".equals(sid)) {
                        accessor.setUser(new User(sid, ((LinkedList<String>) agentList)));
                    }

                }
            }
        }
        return message;
    }
}