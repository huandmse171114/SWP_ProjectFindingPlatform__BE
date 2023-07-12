package com.findhub.finhubbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCategoryResponseModel {
    private int id;
    private int projectCategoryId;
    private String name;
    private String status;
}
