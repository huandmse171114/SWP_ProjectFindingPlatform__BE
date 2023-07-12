package com.findhub.finhubbackend.repository;

import java.util.List;

import com.findhub.finhubbackend.entity.teamProject.TeamProject;

public interface TeamProjectRepository
        extends Repo<TeamProject>{

    List<TeamProject> findAllByProjectId(int id);

    List<TeamProject> findAllByTeamId(int id);
}
