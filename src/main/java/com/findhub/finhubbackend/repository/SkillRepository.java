package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.skill.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>, Repo<Skill> {

    /**
     * tìm chính xác name
     */
    Optional<Skill> findByName(String name);

    /**
     * tìm tất cả Skill có chính xác
     */
    List<Skill> findAllByNameContaining(String name);
}
