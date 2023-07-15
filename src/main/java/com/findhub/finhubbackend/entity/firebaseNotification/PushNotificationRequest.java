package com.findhub.finhubbackend.entity.firebaseNotification;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushNotificationRequest {
    private String title;
    private String message;
    private String topic;
    private String token;
}