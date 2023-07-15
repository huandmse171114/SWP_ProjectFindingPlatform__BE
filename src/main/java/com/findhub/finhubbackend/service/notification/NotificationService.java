package com.findhub.finhubbackend.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.notification.Notification;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public void sendGlobalNotification() {
        Notification n = Notification
            .builder()
                .message("Global Notification")
            .build();

        messagingTemplate.convertAndSend("/topic/global", n);
    }

    public void sendPrivateNotification(final String userId) {
        Notification n = Notification
            .builder()
                .message("Private Notification")
            .build();

        messagingTemplate.convertAndSendToUser(userId, "/topic/private", n);
    }
}
