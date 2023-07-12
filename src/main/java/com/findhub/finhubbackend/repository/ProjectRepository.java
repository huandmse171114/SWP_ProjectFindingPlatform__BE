package com.findhub.finhubbackend.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.dto.ProjectDTO;
import com.findhub.finhubbackend.entity.project.Project;

// @Repository
public interface ProjectRepository extends Repo<Project> {
    Optional<Project> findByName(String name);

    List<Project> findAllByNameContaining(String name);

    // Optional<Project> findByDeliverableTypeId(int id);

    // List<Project> findAllByDeliverableTypeId(int id);

    // description: không làm

    List<Project> findALlByPublisherId(int id);

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

    @Query(value = """
                SELECT *
                FROM Project
                WHERE
                    Name LIKE %:name%
                OR
                    Id LIKE :id%
            """, nativeQuery = true)
    List<ProjectDTO> getAllByNameContainingOrIdLike(
            @Param("id") int id,
            @Param("name") String name);

    // boolean existsByTeamIdAndId(int teamId, int projectId);

}
