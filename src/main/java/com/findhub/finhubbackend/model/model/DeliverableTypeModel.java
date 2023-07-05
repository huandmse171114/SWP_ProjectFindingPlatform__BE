package com.findhub.finhubbackend.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliverableTypeModel {
    // private int projectId;
    private int id;
    private String name;
    private String description;
    private String value;
    private String status;
}
