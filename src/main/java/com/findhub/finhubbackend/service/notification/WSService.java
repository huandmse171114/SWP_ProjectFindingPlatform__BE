package com.findhub.finhubbackend.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.notification.Notification;

@Service
public class WSService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationService notificationService;

    public void notifyFrontend(Object message) {
        Notification n = Notification
            .builder()
                .message(message)
            .build();
        notificationService.sendGlobalNotification();

        messagingTemplate.convertAndSend("/topic/messages", n);
    }

    public void notifyUser(final String id, Object message) {
        Notification n = Notification
            .builder()
                .message(message)
            .build();

        notificationService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id, "/topic/private", n);
    }
}
