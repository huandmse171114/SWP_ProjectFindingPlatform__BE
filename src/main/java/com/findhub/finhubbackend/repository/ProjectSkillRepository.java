package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;

public interface ProjectSkillRepository extends JpaRepository<ProjectSkill, Integer> {

    List<ProjectSkill> findAllByProjectId(int id);

    Optional<ProjectSkill> findById(int id);

    List<ProjectSkill> findAllById(int id);

    List<ProjectSkill> findAllByIdContaining(int id);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE Project_Skill
            SET
                SkillId = :skillId ,
                Level = :level ,
                Status = :status
            WHERE Id = :id
            """, nativeQuery = true)
    void updateById(
        @Param("id") int id,
        @Param("skillId") int skillId,
        @Param("status") int status,
        @Param("level") int level
    );
}
