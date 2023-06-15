package com.findhub.finhubbackend.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.project.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>, Repo<Project> {
    Optional<Project> findByTitle(String title);

    List<Project> findAllByTitleContaining(String title);

    Optional<Project> findByType(String type);

    List<Project> findAllByTypeContaining(String type);

    // description: không làm

    List<Project> findAllByWage(float wage);

    List<Project> findAllByWageGreaterThanEqual(float wage);

    List<Project> findAllByWageLessThanEqual(float wage);

    List<Project> findAllByWageBetween(float start, float end);

    // imageURL: không làm

    List<Project> findAllByDeliverDays(int days);

    List<Project> findAllByDeliverDaysGreaterThanEqual(int days);

    List<Project> findAllByDeliverDaysLessThanEqual(int days);

    List<Project> findAllByPublishDate(Date publishDate);

    List<Project> findAllByPublishDateAfter(Date publishDate);

    List<Project> findAllByPublishDateBefore(Date publishDate);

    boolean existsByTeamIdAndId(int teamId, int projectId);

}
