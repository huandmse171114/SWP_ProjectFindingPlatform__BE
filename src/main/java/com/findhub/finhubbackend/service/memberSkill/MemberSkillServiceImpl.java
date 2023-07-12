package com.findhub.finhubbackend.service.memberSkill;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkillStatus;
import com.findhub.finhubbackend.repository.MemberSkillRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class MemberSkillServiceImpl
        extends ServiceImpl<MemberSkill, MemberSkillRepository, MemberSkillStatus>
        implements MemberSkillService{

}
