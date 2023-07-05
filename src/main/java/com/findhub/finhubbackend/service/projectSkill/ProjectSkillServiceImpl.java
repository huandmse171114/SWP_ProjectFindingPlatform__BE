package com.findhub.finhubbackend.service.projectSkill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkillStatus;
import com.findhub.finhubbackend.repository.ProjectSkillRepository;

@Service
public class ProjectSkillServiceImpl
        implements ProjectSkillService {

    @Autowired
    private ProjectSkillRepository repo;

    @Override
    public ProjectSkill add(ProjectSkill entity) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(ProjectSkill entity) {
        return false;
    }

    @Override
    public List<ProjectSkill> findAllById(int id) {
        return null;
    }

    @Override
    public List<ProjectSkill> findAllByIdContaining(int id) {
        return null;
    }

    @Override
    public List<ProjectSkill> findAllByStatus(int status) {
        return null;
    }

    @Override
    public List<ProjectSkill> findAllByStatus(ProjectSkillStatus status) {
        return null;
    }

    @Override
    public ProjectSkill get(int id) {
        return null;
    }

    @Override
    public List<ProjectSkill> getAll() {
        return null;
    }

    @Override
    public ProjectSkill save(ProjectSkill entity) {
        return repo.save(entity);
    }

    @Override
    public ProjectSkill update(int id, ProjectSkill entity) {
        return null;
    }

    @Override
    public ProjectSkill update(ProjectSkill oldT, ProjectSkill newT) {
        return null;
    }

    @Override
    public boolean updateStatus(int id, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, ProjectSkillStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(int id, Status status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectSkill entity, int status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectSkill entity, ProjectSkillStatus status) {
        return false;
    }

    @Override
    public boolean updateStatus(ProjectSkill entity, Status status) {
        return false;
    }

}
