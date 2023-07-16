package com.findhub.finhubbackend.service.memberSkill;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkillStatus;
import com.findhub.finhubbackend.repository.MemberSkillRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class MemberSkillServiceImpl
        extends ServiceImpl<MemberSkill, MemberSkillRepository, MemberSkillStatus>
        implements MemberSkillService{
	
	@Autowired
	private MemberSkillRepository repo;

	@Override
	public Optional<MemberSkill> findByMemberIdAndSkillId(int memberId, int skillId) {
		return repo.findByMemberIdAndSkillId(memberId, skillId);
	}

	@Override
	public List<MemberSkill> findAllByMemberId(int memberId) {
		return repo.findAllByMemberId(memberId);
	}



}
