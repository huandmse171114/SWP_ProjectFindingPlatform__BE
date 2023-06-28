package com.findhub.finhubbackend.service.projectCategoryDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.projectCategoryDetail.ProjectCategoryDetail;
import com.findhub.finhubbackend.entity.projectCategoryDetail.ProjectCategoryDetailStatus;
import com.findhub.finhubbackend.repository.ProjectCategoryDetailRepository;

@Service
public class ProjectCategoryDetailServiceImpl
        implements ProjectCategoryDetailService {

    @Autowired
    private ProjectCategoryDetailRepository repo;

    @Override
    public ProjectCategoryDetail add(ProjectCategoryDetail entity) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(ProjectCategoryDetail entity) {
        return false;
    }

    @Override
    public List<ProjectCategoryDetail> findAllById(int id) {
        return null;
    }

    @Override
    public List<ProjectCategoryDetail> findAllByIdContaining(int id) {
        return null;
    }

    @Override
    public List<ProjectCategoryDetail> findAllByStatus(int status) {
        return null;
    }

    @Override
    public List<ProjectCategoryDetail> findAllByStatus(ProjectCategoryDetailStatus status) {
        return null;
    }

    @Override
    public ProjectCategoryDetail get(int id) {
        return null;
    }

    @Override
    public List<ProjectCategoryDetail> getAll() {
        return null;
    }

    @Override
    public ProjectCategoryDetail save(ProjectCategoryDetail entity) {
        return repo.save(entity);
    }

    @Override
    public ProjectCategoryDetail update(int id, ProjectCategoryDetail entity) {
        return null;
    }

    @Override
    public ProjectCategoryDetail update(ProjectCategoryDetail oldT, ProjectCategoryDetail newT) {
        return null;
    }

    @Override
    public boolean updateStatus(int id, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, ProjectCategoryDetailStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, Status status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectCategoryDetail entity, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectCategoryDetail entity, ProjectCategoryDetailStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectCategoryDetail entity, Status status) {
        return false;
    }

}
