package com.findhub.finhubbackend.service.firebaseNotification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Notification;

public class FirebaseNotificationSender {

    private static final String FIREBASE_SERVER_KEY = "AAAAkB4-t6o:APA91bE4KFtY6vkZAEDmaPI7u4aXX1yyyUgzDZVns318ORHmL3QDG5fdJcqlwKKJI1qWtBUWypjfBFGmxT3LM0qq8D2bH6vqVz9b4ovHfGPyOJVSraxrIE47BB7Obahgz1IuOfK3PnUp";

    public static void sendNotification(String userId, String title, String message) {
        // FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();

        // // Create a notification message
        // Notification notification = new Notification(title, message);

        // // Build a notification payload
        // NotificationPayload notificationPayload = new NotificationPayload(notification);

        // // Send the notification to the user
        // firebaseMessaging.send(notificationPayload, userId);
    }
}