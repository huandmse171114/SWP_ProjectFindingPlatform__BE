package com.findhub.finhubbackend.service.skill;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.repository.SkillRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class SkillServiceImpl extends ServiceImpl<Skill, SkillRepository, SkillStatus>
        implements SkillService {

    @Override
    public List<Skill> findAllByNameLike(String name) {
        return repo.findAllByNameLike(name);
    }

    @Override
    public Optional<Skill> findByName(String name) {
        return repo.findByName(name);
    }
}
