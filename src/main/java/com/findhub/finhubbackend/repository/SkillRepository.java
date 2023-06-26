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
				s.Name AS name,
				psr.Level AS level
			FROM
                ProjectSkillRequire psr
            LEFT JOIN
                Skill s ON psr.SkillId = s.Id
            WHERE
                psr.ProjectId = :id
            ORDER BY
                psr.Level ASC
			""", nativeQuery = true)
    List<SkillDTO> getNameAndLevelByProjectId(@Param("id") int id);

    @Query(value = """
            SELECT
                s.Name,
                msd.Level
            FROM
                Skill s
            JOIN
                MemberSkillDetail msd ON s.Id = msd.SkillId
            WHERE
                msd.MemberId = :id
            ORDER BY
                s.Id ASC
			""", nativeQuery = true)
    List<SkillDTO> getNameByMemberId(@Param("id") int id);

    @Query(value = """
            SELECT
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
