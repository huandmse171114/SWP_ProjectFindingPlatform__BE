package com.findhub.finhubbackend.service.memberSkill;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkillStatus;
import com.findhub.finhubbackend.repository.MemberSkillRepository;
import com.findhub.finhubbackend.service.service.Service;

public interface MemberSkillService
        extends Service<MemberSkill, MemberSkillStatus>{
	public Optional<MemberSkill> findByMemberIdAndSkillId(int memberId, int skillId);

	public List<MemberSkill> findAllByMemberId(int memberId);
}
