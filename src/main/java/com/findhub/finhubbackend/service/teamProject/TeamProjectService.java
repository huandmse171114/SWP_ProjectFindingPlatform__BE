package com.findhub.finhubbackend.service.teamProject;

import java.util.List;

import com.findhub.finhubbackend.entity.teamProject.TeamProject;
import com.findhub.finhubbackend.entity.teamProject.TeamProjectStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface TeamProjectService
        extends Service<TeamProject, TeamProjectStatus>{

    public List<TeamProject> findAllByProjectId(int id);

    public List<TeamProject> findAllByTeamId(int id);

}
