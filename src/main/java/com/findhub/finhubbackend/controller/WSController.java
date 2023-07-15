package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.notification.Notification;
import com.findhub.finhubbackend.service.notification.WSService;

@RestController
public class WSController {

    @Autowired
    private WSService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Notification n) {
        service.notifyFrontend(n.getMessage());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody Notification n) {
        service.notifyUser(id, n.getMessage());
    }
}