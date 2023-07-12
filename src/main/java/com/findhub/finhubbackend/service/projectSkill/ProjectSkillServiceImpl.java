package com.findhub.finhubbackend.service.projectSkill;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkillStatus;
import com.findhub.finhubbackend.repository.ProjectSkillRepository;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.service.ServiceImpl;
import com.findhub.finhubbackend.service.skill.SkillService;

@Service
public class ProjectSkillServiceImpl
        extends ServiceImpl<ProjectSkill, ProjectSkillRepository, ProjectSkillStatus>
        implements ProjectSkillService {

    @Autowired
    private ProjectSkillRepository repo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SkillService skillService;

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
        Optional<ProjectSkill> ps = repo.findById(id);
        return (ps.isPresent()) ? ps.get() : null;
    }

    @Override
    public void updateById(int id, int skillId, int status, int level, int projectId) {
        if(get(id) != null)
            repo.updateById(id, skillId, status, level);
        else
            save(
                ProjectSkill
                    .builder()
                        .project(
                            projectService.get(projectId)
                            )
                        .skill(
                            skillService.get(skillId)
                        )
                    .build()
        );
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
        entity.setId(id);
        return repo.save(entity);
    }

    @Override
    public ProjectSkill update(ProjectSkill entity) {
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
