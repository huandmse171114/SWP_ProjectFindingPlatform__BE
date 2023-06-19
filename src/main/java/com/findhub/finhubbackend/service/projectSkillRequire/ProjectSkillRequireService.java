package com.findhub.finhubbackend.service.projectSkillRequire;

import java.util.List;

import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;

public interface ProjectSkillRequireService {
    /**
     * add new T to DB
     */
    public ProjectSkillRequire add(ProjectSkillRequire entity);

    /**
     * update T
     */
    public ProjectSkillRequire update(int id, ProjectSkillRequire entity);

    /**
     * update T
     */
    public ProjectSkillRequire update(ProjectSkillRequire oldT, ProjectSkillRequire newT);

    /**
     * delete existed T from DB
     */
    public boolean delete(int id);

    /**
     * delete existed T from DB
     */
    public boolean delete(ProjectSkillRequire entity);

    /**
     * find T by id (exact id)
     */
    public ProjectSkillRequire findById(int id);
    public ProjectSkillRequire findByProjectId(int id);

    public List<ProjectSkillRequire> findAllById(int id);
    public List<ProjectSkillRequire> findAllByProjectId(int id);

    /**
     * get all Ts in DB
     */
    public List<ProjectSkillRequire> getAll();

    /**
     * save T
     */
    public ProjectSkillRequire save(ProjectSkillRequire entity);
}
