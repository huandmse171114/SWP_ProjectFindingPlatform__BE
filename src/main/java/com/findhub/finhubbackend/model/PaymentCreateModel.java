package com.findhub.finhubbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCreateModel {
    private int projectId;
    private int teamId;
    private float amount;
    private String description;
}
