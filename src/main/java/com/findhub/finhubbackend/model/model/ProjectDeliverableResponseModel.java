package com.findhub.finhubbackend.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDeliverableResponseModel {
    private int id;
    private int projectDeliverableId;
    private String name;
    private String description;
    private String value;
    private String status;
}
