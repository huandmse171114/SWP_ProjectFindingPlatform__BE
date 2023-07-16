package com.findhub.finhubbackend.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinModel {
    private int senderId; //accId
    private int receiverId; //accId
    private int teamId;
    private String message;
}
