package com.findhub.finhubbackend.service.projectDeliverable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverableStatus;
import com.findhub.finhubbackend.repository.ProjectDeliverableRepository;
import com.findhub.finhubbackend.service.deliverable.DeliverableService;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class ProjectDeliverableServiceImpl
        extends ServiceImpl<ProjectDeliverable, ProjectDeliverableRepository, ProjectDeliverableStatus>
        implements ProjectDeliverableService {

    @Autowired
    private DeliverableService deliverableService;

    @Autowired
    private ProjectService projectService;

    @Override
    public List<ProjectDeliverable> findAllByProjectId(int id) {
        return repo.findAllByProjectId(id);
    }

    @Override
    public void updateById(int id, int deliverableTypeId, String value, int status, String description, int projectId) {
        if(get(id) != null)
            repo.updateById(id, deliverableTypeId, value, status, description);
        else save(
            ProjectDeliverable
                .builder()
                    .project(
                        projectService.get(projectId)
                    )
                    .deliverableType(
                        deliverableService.get(deliverableTypeId)
                    )
                .build()
        );
    }
}
