package com.findhub.finhubbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.mail.MailRequest;
import com.findhub.finhubbackend.service.email.EmailService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/test")
public class TestController {

    @Autowired
    private EmailService service;

    // @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());
        model.put("location", "Bangalore,India");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model));
    }
}
