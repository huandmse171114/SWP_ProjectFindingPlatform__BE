package com.findhub.finhubbackend.service.projectDeliverable;

import java.util.List;

import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverableStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface ProjectDeliverableService
        extends Service<ProjectDeliverable, ProjectDeliverableStatus> {
    public List<ProjectDeliverable> findAllByProjectId(int id);

}
