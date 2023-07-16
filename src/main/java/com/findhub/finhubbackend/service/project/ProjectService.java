package com.findhub.finhubbackend.service.project;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.dto.ProjectDTO;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.service.service.Service;

public interface ProjectService
        extends Service<Project, ProjectStatus> {

    public Optional<Project> findByName(String name);

    public List<Project> findAllByNameContaining(String name);

    // public Optional<Project> findByDeliverableTypeId(int id);

    // public List<Project> findAllByDeliverableTypeId(int id);

    // description: không làm

    public List<Project> findALlByPublisherId(int id);

    public List<Project> findAllByWage(float wage);

    public List<Project> findAllByWageGreaterThanEqual(float wage);

    public List<Project> findAllByWageLessThanEqual(float wage);

    public List<Project> findAllByWageBetween(float start, float end);

    // imageURL: không làm

    public List<Project> findAllByDeliverDays(int days);

    public List<Project> findAllByDeliverDaysGreaterThanEqual(int days);

    public List<Project> findAllByDeliverDaysLessThanEqual(int days);

    public List<Project> findAllByPublishDate(Date publishDate);

    public List<Project> findAllByPublishDateAfter(Date publishDate);

    public List<Project> findAllByPublishDateBefore(Date publishDate);

    public List<ProjectDTO> getAllByNameContainingOrIdLike(int id, String name);

    public ProjectResponseModel getById(int id);

    public List<ProjectResponseModel> getAllByIdContaining(int id);

    public List<ProjectResponseModel> getAllByNameContaining(String name);

    public List<ProjectResponseModel> getAllByNameContainingOrIdLike(String input);

    public ProjectResponseModel getModel(int id);

	public List<Project> getAllByPublisherId(int id);


    // public boolean existsByTeamIdAndProjectId(int teamId, int projectId);

}
