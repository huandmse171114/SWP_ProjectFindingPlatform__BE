package com.findhub.finhubbackend.repository;

import java.util.List;

import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;

public interface ProjectDeliverableRepository extends Repo<ProjectDeliverable>{
    List<ProjectDeliverable> findAllByProjectId(int id);
}
