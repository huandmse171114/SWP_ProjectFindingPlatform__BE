package com.findhub.finhubbackend.service.projectCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategoryStatus;
import com.findhub.finhubbackend.repository.ProjectCategoryRepository;

@Service
public class ProjectCategoryServiceImpl
        implements ProjectCategoryService {

    @Autowired
    private ProjectCategoryRepository repo;

    @Override
    public ProjectCategory update(ProjectCategory entity) {
        return null;
    }

    @Override
    public ProjectCategory add(ProjectCategory entity) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(ProjectCategory entity) {
        return false;
    }

    @Override
    public List<ProjectCategory> findAllById(int id) {
        return null;
    }

    @Override
    public List<ProjectCategory> findAllByIdContaining(int id) {
        return null;
    }

    @Override
    public List<ProjectCategory> findAllByStatus(int status) {
        return null;
    }

    @Override
    public List<ProjectCategory> findAllByStatus(ProjectCategoryStatus status) {
        return null;
    }

    @Override
    public ProjectCategory get(int id) {
        return null;
    }

    @Override
    public List<ProjectCategory> getAll() {
        return null;
    }

    @Override
    public ProjectCategory save(ProjectCategory entity) {
        return repo.save(entity);
    }

    @Override
    public ProjectCategory update(int id, ProjectCategory entity) {
        return null;
    }

    @Override
    public ProjectCategory update(ProjectCategory oldT, ProjectCategory newT) {
        return null;
    }

    @Override
    public boolean updateStatus(int id, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, ProjectCategoryStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, Status status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectCategory entity, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectCategory entity, ProjectCategoryStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectCategory entity, Status status) {
        return false;
    }

}
