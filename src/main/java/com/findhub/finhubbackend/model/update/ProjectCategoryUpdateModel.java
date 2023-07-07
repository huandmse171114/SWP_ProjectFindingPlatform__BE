package com.findhub.finhubbackend.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCategoryUpdateModel {
    private int id;
    private int categoryId;
    private int projectId;
    private int status;
}
