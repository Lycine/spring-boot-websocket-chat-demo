package com.example.websocketdemo;

import com.example.websocketdemo.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@WebListener
public class MyServletContextListener implements ServletContextListener {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");


        String destination = "/user/123/agent";
        TimerTask task = new TimerTask() {
            @Override
            public void run() {



                String greetingContent = "broadcast in '" + destination + "' , now is: " + LocalTime.now().toString();
                System.out.println(greetingContent);
                messagingTemplate.convertAndSend(destination, new Greeting(greetingContent));
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 3000, 3000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    class ScheduledExecutor implements Runnable {
        ScheduledExecutor() {
        }

        @Override
        public void run() {

        }
    }
}