package com.findhub.finhubbackend.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkillStatus;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.model.model.MemberModel;
import com.findhub.finhubbackend.model.model.MemberSkillModel;
import com.findhub.finhubbackend.model.model.SkillModel;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.MajorResponseModel;
import com.findhub.finhubbackend.model.update.MemberUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.MemberUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateSkillModel;
import com.findhub.finhubbackend.model.update.PublisherUpdateDescriptionModel;
import com.findhub.finhubbackend.repository.MemberRepository;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.service.memberSkill.MemberSkillService;
import com.findhub.finhubbackend.service.service.ServiceImpl;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Utils;

@Service
public class MemberServiceImpl extends ServiceImpl<Member, MemberRepository, MemberStatus>
        implements MemberService {

    @Override
    public boolean isExistedInMember(int skillId, int memberId) {
        Member m = get(memberId);
        for(var s : m.getSkills())
            if(skillId == s.getSkill().getId()) return true;
        return false;
    }
    
    private boolean isUpdateMemberStatus = true;

    @Autowired
    private SkillService skillService;

    @Autowired
    private MajorService majorService;
    
    @Autowired
    private MemberSkillService memberSkillService;

    @Override
    public List<Member> findAllByBalance(float balance) {
        return repo.findAllByBalance(balance);
    }

    @Override
    public List<MemberDTO> getAllByNameContaining(String name) {
        return repo.getAllByNameContaining(name);
    }

    @Override
    public List<MemberDTO> getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(String input) {
        int id = Integer.parseInt(input);
        return repo.getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(id, input, input, input);
    }

    @Override
    public List<Member> findAllByBalanceBetween(float start, float end) {
        return repo.findAllByBalanceBetween(start, end);
    }

    @Override
    public List<Member> findAllByEmailContaining(String email) {
        return repo.findAllByEmailContaining(email);
    }

    @Override
    public List<Member> findAllByMajorId(int id) {
        return repo.findAllByMajorId(id);
    }

    @Override
    public List<MemberDTO> getAllByTeamId(int id) {
        return repo.getAllByTeamId(id);
    }

    @Override
    public List<Member> findAllByNameContaining(String name) {
        return repo.findAllByNameContaining(name);
    }

    @Override
    public List<Member> findAllByPhoneLike(String phone) {
        return repo.findAllByPhoneLike(phone);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
        return repo.findByPhone(phone);
    }
    
    public MemberModel getModel(int id) {
        Member member = get(id);
        
        if (member.getStatus() == MemberStatus.DELETED.getValue()) return null;
        
        StatusModel status = StatusModel.builder()
        		.id(member.getStatus())
        		.name(MemberStatus.nameOf(
                    member.getStatus()
                ))
        		.build();

        MemberModel result = MemberModel
            .builder()
                .id(id)
                .email(member.getEmail())
                .name(member.getName())
                .description(member.getDescription())
                .balance(member.getBalance())
                .status(status)
            .build();
        
        if (member.getStatus() != MemberStatus.GENERATED.getValue() && member.getName() != null) {
        	List<SkillModel> skills = new ArrayList<>();
            skillService.getNameAndLevelByMemberId(id)
                .forEach(each -> skills.add(
                    SkillModel
                        .builder()
                            .id(each.getId())
                            .name(each.getName())
                            .level(each.getLevel())
                            .status(StatusModel.builder()
                            		.id(each.getStatus())
                            		.name(SkillStatus.nameOf(each.getStatus()))
                            		.build())
                        .build()
                )
            ); 
     
            
        	Major major = majorService.get(member.getMajorId());   
        	result.setMajor(MajorResponseModel
	        		  .builder()
	        		  .id(major.getId())
		              .code(major.getCode())
		              .name(major.getName())
		              .build());
        	result.setDOB(member.getDob().toString());
        	result.setPhone(member.getPhone());
        	result.setName(member.getName());
        	result.setSkills(skills);
        }
        return result;
    }

	@Override
	public MemberModel getModel(String email) {
		Optional<Member> memberOption = findByEmail(email);
        
		if (memberOption.isEmpty()) return null;
		Member member = memberOption.get();
		
        if (member.getStatus() == MemberStatus.DELETED.getValue()) return null;
        
        StatusModel status = StatusModel.builder()
        		.id(member.getStatus())
        		.name(MemberStatus.nameOf(
                    member.getStatus()
                ))
        		.build();

        MemberModel result = MemberModel
            .builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .description(member.getDescription())
                .balance(member.getBalance())
                .status(status)
            .build();
        
        if (member.getStatus() != MemberStatus.GENERATED.getValue() && member.getName() != null) {
        	List<SkillModel> skills = new ArrayList<>();
        	skillService.getNameAndLevelByMemberId(member.getId())
	            .forEach(each -> skills.add(
	                SkillModel
	                    .builder()
	                        .id(each.getId())
	                        .name(each.getName())
	                        .level(each.getLevel())
	                        .status(StatusModel.builder()
	                        		.id(each.getStatus())
	                        		.name(SkillStatus.nameOf(each.getStatus()))
	                        		.build())
	                    .build()
	            )
	        ); 
            
        	Major major = majorService.get(member.getMajorId());   
        	result.setMajor(MajorResponseModel
	        		  .builder()
	        		  .id(major.getId())
		              .code(major.getCode())
		              .name(major.getName())
		              .build());
        	result.setDOB(member.getDob().toString());
        	result.setPhone(member.getPhone());
        	result.setName(member.getName());
        	result.setSkills(skills);
        }
        return result;
	}

	@Override
	public boolean update(MemberUpdateModel m) {
		System.out.println(m.getId() + m.getName()+ m.getEmail()+ m.getPhone()+ m.getMajorId()+ m.getAvatarURL()+ m.getDob()+ m.getStatus().getId());
		repo.update(m.getId(), m.getName(), m.getEmail(), m.getPhone(), m.getMajorId(), m.getAvatarURL(), m.getDob(), m.getStatus().getId());
		return true;
	}

	@Override
	public boolean updateDescription(MemberUpdateDescriptionModel m) {
		repo.updateDescription(m.getId(), m.getDescription());
		return true;
	}

	@Override
	public boolean updateSkill(MemberUpdateSkillModel m, Member member) {
		isUpdateMemberStatus = true;
		List<MemberSkillModel> skills = m.getSkills();
		skills.forEach(skill -> {
			MemberSkill mSkill = MemberSkill.builder()
					.member(member)
					.skill(skillService.get(skill.getId()))
					.level(skill.getLevel())
					.build();
			if (skill.getStatus() != null) {
				if (isUpdateMemberStatus) { //continue to check if the flag is still true, stop checking when it is false
					isUpdateMemberStatus = skill.getStatus() != MemberSkillStatus.VALIDATING.getValue();				
					System.out.println("flag: "+ skill.getId() + " " + isUpdateMemberStatus + " " + skill.getStatus());
				}
				mSkill.setStatus(skill.getStatus());
			}
			Optional<MemberSkill> existSkill = memberSkillService.findByMemberIdAndSkillId(member.getId(), skill.getId());
			if (existSkill.isPresent()) mSkill.setId(existSkill.get().getId());
			
			memberSkillService.save(mSkill);
		});
		if (isUpdateMemberStatus) {
			System.out.println("update");
			repo.updateStatus(member.getId(), MemberStatus.VERIFIED.getValue());
		}else {
			System.out.println("not update");
			repo.updateStatus(member.getId(), MemberStatus.INFORMED.getValue());
		}
		return true;
	}

}
