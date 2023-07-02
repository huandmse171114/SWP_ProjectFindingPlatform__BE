package com.findhub.finhubbackend.service.projectOutput;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.projectOutput.ProjectOutput;
import com.findhub.finhubbackend.entity.projectOutput.ProjectOutputStatus;
import com.findhub.finhubbackend.repository.ProjectOutputRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class ProjectOutputServiceImpl
        extends ServiceImpl<ProjectOutput, ProjectOutputRepository, ProjectOutputStatus>
        implements ProjectOutputService {

    @Override
    public List<ProjectOutput> findAllByProjectId(int id) {
        return repo.findAllByProjectId(id);
    }

}
