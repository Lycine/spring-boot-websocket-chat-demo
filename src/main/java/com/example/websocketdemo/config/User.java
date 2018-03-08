package com.example.websocketdemo.config;

import java.security.Principal;
import java.util.LinkedList;

public final class User implements Principal {

    private final String name;
    private LinkedList<String> agentList;

    public User(String name, LinkedList<String> agentList) {
        this.name = name;
        this.agentList = agentList;
    }

    public LinkedList<String> getAgentList() {
        return agentList;
    }

    public void setAgentList(LinkedList<String> agentList) {
        this.agentList = agentList;
    }

    @Override
    public String getName() {
        return name;
    }
}