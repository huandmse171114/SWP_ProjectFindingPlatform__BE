package com.findhub.finhubbackend.service.skill;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.SkillDTO;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.SkillResponseModel;
import com.findhub.finhubbackend.model.update.SkillUpdateModel;
import com.findhub.finhubbackend.repository.SkillRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class SkillServiceImpl
        extends ServiceImpl<Skill, SkillRepository, SkillStatus>
        implements SkillService {

    @Override
    public List<SkillDTO> getNameAndLevelByProjectId(int id) {
        return repo.getNameAndLevelByProjectId(id);
    }

    @Override
    public List<Skill> findAllByNameContaining(String name) {
        return repo.findAllByNameContaining(name);
    }

    @Override
    public Optional<Skill> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<SkillDTO> getAllByNameContainingOrIdLike(int id, String name, int level) {
        return repo.getAllByNameContainingOrIdLike(id, name);
    }

    @Override
    public List<SkillDTO> getAllByNameContaining(String name) {
        return repo.getAllByNameContaining(name);
    }

    @Override
    public List<SkillDTO> getNameAndLevelByMemberId(int id) {
        return repo.getNameByMemberId(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }
    
    @Override
    public SkillResponseModel getModel(int id) {
    	Skill s = get(id);
    	SkillResponseModel result = SkillResponseModel.builder()
    			.id(s.getId())
    			.name(s.getName())
    			.status(new StatusModel(s.getStatus(),SkillStatus.nameOf(s.getStatus())))
    			.build();
    	return result;
    }

	@Override
	public boolean update(SkillUpdateModel skill) {
		repo.update(skill.getId(), skill.getName());
		return true;
	}

}
