package com.findhub.finhubbackend.service.projectSkillRequire;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;
import com.findhub.finhubbackend.repository.ProjectSkillRequireRepository;

@Service
public class ProjectSkillRequireServiceImpl implements ProjectSkillRequireService {

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
        return repo.findAllById(id);
    }

    @Override
    public List<ProjectSkillRequire> findAllByProjectId(int id) {
        return repo.findAllByProjectId(id);
    }

    @Override
    public ProjectSkillRequire findById(int id) {
        Optional<ProjectSkillRequire> psr = repo.findById(id);
        return (psr.isPresent()) ? psr.get() : null;
    }

    @Override
    public ProjectSkillRequire findByProjectId(int id) {
        return null;
    }

    @Override
    public List<ProjectSkillRequire> getAll() {
        return null;
    }

    @Override
    public ProjectSkillRequire save(ProjectSkillRequire entity) {
        return null;
    }

    @Override
    public ProjectSkillRequire update(int id, ProjectSkillRequire entity) {
        return null;
    }

    @Override
    public ProjectSkillRequire update(ProjectSkillRequire oldT, ProjectSkillRequire newT) {
        return null;
    }

}
