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
import com.findhub.finhubbackend.entity.mail.MailType;
import com.findhub.finhubbackend.service.email.EmailService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MAIL)
public class MailController {

    @Autowired
    private EmailService service;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model, MailType.CONFRIM_RECEIVE_APPLICATION));
    }

    public ResponseEntity<?> sendConfirmReceivedApplicationFormEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model, MailType.CONFRIM_RECEIVE_APPLICATION));
    }

    public ResponseEntity<?> sendJoinRequestEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model, MailType.JOIN));
    }

    public ResponseEntity<?> sendInviteRequestEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model, MailType.INVITE));
    }

    public ResponseEntity<?> sendRejectEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model, MailType.REJECTED));
    }

    public ResponseEntity<?> sendApproveEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendEmail(request, model, MailType.APPROVED));
    }
}
