package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;

public interface MemberSkillRepository
        extends Repo<MemberSkill>{

	Optional<MemberSkill> findByMemberIdAndSkillId(int memberId, int skillId);

	List<MemberSkill> findAllByMemberId(int memberId);

}
