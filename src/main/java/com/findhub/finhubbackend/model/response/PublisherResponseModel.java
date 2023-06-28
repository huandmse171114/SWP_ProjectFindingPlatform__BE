package com.findhub.finhubbackend.model.response;

import java.sql.Date;

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
    private Date DOB;
    private String phone;
    private float balance;
    private String status;
}
