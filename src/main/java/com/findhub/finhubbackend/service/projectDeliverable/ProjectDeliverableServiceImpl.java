package com.findhub.finhubbackend.service.projectDeliverable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverableStatus;
import com.findhub.finhubbackend.repository.ProjectDeliverableRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class ProjectDeliverableServiceImpl
        extends ServiceImpl<ProjectDeliverable, ProjectDeliverableRepository, ProjectDeliverableStatus>
        implements ProjectDeliverableService {

    @Override
    public List<ProjectDeliverable> findAllByProjectId(int id) {
        return repo.findAllByProjectId(id);
    }

}
