package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.dto.SkillDTO;
import com.findhub.finhubbackend.entity.skill.Skill;

// @Repository
public interface SkillRepository extends Repo<Skill> {

    /**
     * tìm chính xác name
     */
    Optional<Skill> findByName(String name);

    /**
     * tìm tất cả Skill có chính xác
     */
    List<Skill> findAllByNameContaining(String name);

    @Query(value = """
            SELECT
                s.Id,
				s.Name AS name,
				ps.Level AS level
			FROM
                Project_Skill ps
            LEFT JOIN
                Skill s ON ps.SkillId = s.Id
            WHERE
                ps.ProjectId = :id
            ORDER BY
                ps.Level ASC
			""", nativeQuery = true)
    List<SkillDTO> getNameAndLevelByProjectId(@Param("id") int id);

    @Query(value = """
            SELECT
                s.Id,
                s.Name,
                ms.Level
            FROM
                Skill s
            JOIN
                Member_Skill ms ON s.Id = ms.SkillId
            WHERE
                ms.MemberId = :id
            ORDER BY
                s.Id ASC
			""", nativeQuery = true)
    List<SkillDTO> getNameByMemberId(@Param("id") int id);

    @Query(value = """
            SELECT
                Id,
                Name
            FROM
                Skill
            WHERE
                Name LIKE %:name%
            OR
                Id LIKE :id%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<SkillDTO> getAllByNameContainingOrIdLike(
            @Param("id") int id,
            @Param("name") String name);

    @Query(value = """
            SELECT
                Id,
                Name
            FROM
                Skill
            WHERE
                Name LIKE %:name%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<SkillDTO> getAllByNameContaining(@Param("name") String name);

    boolean existsByName(String name);
}
