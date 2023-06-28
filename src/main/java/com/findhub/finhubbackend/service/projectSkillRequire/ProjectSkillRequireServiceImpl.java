package com.findhub.finhubbackend.service.projectSkillRequire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;
import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequireStatus;
import com.findhub.finhubbackend.repository.ProjectSkillRequireRepository;

@Service
public class ProjectSkillRequireServiceImpl
        implements ProjectSkillRequireService {

    @Autowired
    private ProjectSkillRequireRepository repo;

    @Override
    public ProjectSkillRequire add(ProjectSkillRequire entity) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(ProjectSkillRequire entity) {
        return false;
    }

    @Override
    public List<ProjectSkillRequire> findAllById(int id) {
        return null;
    }

    @Override
    public List<ProjectSkillRequire> findAllByIdContaining(int id) {
        return null;
    }

    @Override
    public List<ProjectSkillRequire> findAllByStatus(int status) {
        return null;
    }

    @Override
    public List<ProjectSkillRequire> findAllByStatus(ProjectSkillRequireStatus status) {
        return null;
    }

    @Override
    public ProjectSkillRequire get(int id) {
        return null;
    }

    @Override
    public List<ProjectSkillRequire> getAll() {
        return null;
    }

    @Override
    public ProjectSkillRequire save(ProjectSkillRequire entity) {
        return repo.save(entity);
    }

    @Override
    public ProjectSkillRequire update(int id, ProjectSkillRequire entity) {
        return null;
    }

    @Override
    public ProjectSkillRequire update(ProjectSkillRequire oldT, ProjectSkillRequire newT) {
        return null;
    }

    @Override
    public boolean updateStatus(int id, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, ProjectSkillRequireStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, Status status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectSkillRequire entity, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectSkillRequire entity, ProjectSkillRequireStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectSkillRequire entity, Status status) {
        return false;
    }

}
