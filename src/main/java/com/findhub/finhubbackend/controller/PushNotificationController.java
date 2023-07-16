package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.firebaseNotification.PushNotificationRequest;
import com.findhub.finhubbackend.entity.firebaseNotification.PushNotificationResponse;
import com.findhub.finhubbackend.service.firebaseNotification.PushNotificationService;


@RestController
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;


    @PostMapping("/notification/token")
    public ResponseEntity<?> sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("send");
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
}