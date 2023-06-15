package com.findhub.finhubbackend.service.project;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface ProjectService extends Service<Project, ProjectStatus> {

    public Optional<Project> findByTitle(String title);

    public List<Project> findAllByTitleContaining(String title);

    public Optional<Project> findByType(String type);

    public List<Project> findAllByTypeContaining(String type);

    // description: không làm

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

    public boolean existsByTeamIdAndProjectId(int teamId, int projectId);

}
