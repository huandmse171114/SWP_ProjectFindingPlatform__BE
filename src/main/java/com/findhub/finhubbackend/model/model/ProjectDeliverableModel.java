package com.findhub.finhubbackend.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDeliverableModel {
    // private int projectId;
    private String name;
    private String description;
    private int deliverableTypeId;
    private int status;
}
