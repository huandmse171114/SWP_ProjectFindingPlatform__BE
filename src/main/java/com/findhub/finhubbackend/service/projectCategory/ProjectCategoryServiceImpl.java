package com.findhub.finhubbackend.service.projectCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategoryStatus;
import com.findhub.finhubbackend.repository.ProjectCategoryRepository;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class ProjectCategoryServiceImpl
        extends ServiceImpl<ProjectCategory, ProjectCategoryRepository, ProjectCategoryStatus>
        implements ProjectCategoryService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProjectService projectService;

    @Override
    public void updateById(int id, int status, int categoryId, int projectId) {
        if (get(id) != null) repo.updateById(id, status, categoryId);
        else
            save(
                ProjectCategory
                    .builder()
                        .category(
                            categoryService.get(categoryId)
                        )
                        .project(
                            projectService.get(projectId)
                        )
                    .build()
            );
    }
}
