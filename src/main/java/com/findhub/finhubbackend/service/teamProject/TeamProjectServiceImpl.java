package com.findhub.finhubbackend.service.teamProject;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.teamProject.TeamProject;
import com.findhub.finhubbackend.entity.teamProject.TeamProjectStatus;
import com.findhub.finhubbackend.repository.TeamProjectRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class TeamProjectServiceImpl
        extends ServiceImpl<TeamProject, TeamProjectRepository, TeamProjectStatus>
        implements TeamProjectService{

    @Override
    public List<TeamProject> findAllByProjectId(int id) {
        return repo.findAllByProjectId(id);
    }

    @Override
    public List<TeamProject> findAllByTeamId(int id) {
        return repo.findAllByTeamId(id);
    }
}
