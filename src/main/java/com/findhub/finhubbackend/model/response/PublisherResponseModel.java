package com.findhub.finhubbackend.model.response;

import java.sql.Date;

import com.findhub.finhubbackend.model.model.StatusModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherResponseModel {
    private int id;
    private String email;
    private String name;
    private String DOB;
    private String phone;
    private float balance;
    private String description;
    private StatusModel status;
}
