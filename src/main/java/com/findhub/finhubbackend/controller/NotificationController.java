package com.findhub.finhubbackend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.findhub.finhubbackend.entity.notification.Notification;
import com.findhub.finhubbackend.service.notification.NotificationService;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/global")
    @SendTo("/topic/global")
    public ResponseEntity<?> sendNotification(Notification noti){
        return ResponseEntity
                    .ok()
                    .body(noti);
    }

    @MessageMapping("/private")
    @SendToUser("/topic/private")
    public ResponseEntity<?> getPrivateMessage(final Notification noti,
                                                final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName());
        return ResponseEntity
                    .ok()
                    .body(
                        noti
                    );
    }
}
