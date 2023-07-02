package com.findhub.finhubbackend.repository;

import java.util.List;

import com.findhub.finhubbackend.entity.projectOutput.ProjectOutput;

public interface ProjectOutputRepository extends Repo<ProjectOutput> {
    List<ProjectOutput> findAllByProjectId(int id);
}
