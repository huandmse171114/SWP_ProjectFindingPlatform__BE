package com.findhub.finhubbackend.entity.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailRequest {
    private String name;
    private String to;
    // private String from;
    private String subject;
}