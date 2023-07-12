package com.findhub.finhubbackend.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDeliverableUpdateModel {
    private int id;
    private int projectId;
    private int deliverableId;
    private String description;
    private String value;
    private int status;
}
