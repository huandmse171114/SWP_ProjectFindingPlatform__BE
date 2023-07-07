package com.findhub.finhubbackend.service.projectSkill;

import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkillStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface ProjectSkillService
        extends Service<ProjectSkill, ProjectSkillStatus> {

    void updateById(int id, int skillId, int status, int level, int projectId);
}
