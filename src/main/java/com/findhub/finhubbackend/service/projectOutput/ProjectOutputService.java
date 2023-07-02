package com.findhub.finhubbackend.service.projectOutput;

import java.util.List;

import com.findhub.finhubbackend.entity.projectOutput.ProjectOutput;
import com.findhub.finhubbackend.entity.projectOutput.ProjectOutputStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface ProjectOutputService
        extends Service<ProjectOutput, ProjectOutputStatus> {
    public List<ProjectOutput> findAllByProjectId(int id);

}
