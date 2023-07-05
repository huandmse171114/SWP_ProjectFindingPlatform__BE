package com.findhub.finhubbackend.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamResponseModel {
    private int id;
    private String name;
    // private PublisherDTO publisher;
    private List<?> members;
    private float balance;
    private String status;
}
