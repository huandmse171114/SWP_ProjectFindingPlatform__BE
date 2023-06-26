package com.findhub.finhubbackend.service.skill;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.dto.SkillDTO;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface SkillService extends Service<Skill, SkillStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<Skill> findByName(String name);

    /**
     * tìm tất cả Skill có chính xác
     */
    public List<Skill> findAllByNameContaining(String name);

    public List<SkillDTO> getNameAndLevelByProjectId(int id);

    public List<SkillDTO> getAllByNameContaining(String name);

    // public List<SkillDTO> getNameAndLevelByNameContainingOrLevelLike(String name,
    // int level);

    public List<SkillDTO> getNameAndLevelByMemberId(int id);

    public List<SkillDTO> getAllByNameContainingOrIdLike(int id, String name, int level);

    public boolean existsByName(String name);

}
