package com.findhub.finhubbackend.service.projectCategory;

import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategoryStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface ProjectCategoryService
        extends Service<ProjectCategory, ProjectCategoryStatus> {

    void updateById(int id, int status, int categoryId, int projectId);
}
