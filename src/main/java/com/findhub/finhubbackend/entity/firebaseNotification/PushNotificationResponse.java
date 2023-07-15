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
public class PushNotificationResponse {
    private int status;
    private String message;
}
