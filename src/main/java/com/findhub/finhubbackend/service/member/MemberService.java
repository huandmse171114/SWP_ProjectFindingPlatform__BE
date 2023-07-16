package com.findhub.finhubbackend.service.member;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.model.model.MemberModel;
import com.findhub.finhubbackend.model.update.MemberUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.MemberUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateSkillModel;
import com.findhub.finhubbackend.model.update.PublisherUpdateDescriptionModel;
import com.findhub.finhubbackend.service.service.Service;

public interface MemberService extends Service<Member, MemberStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<Member> findByName(String name);

    /**
     * tìm tất cả Member có chính xác
     */
    public List<Member> findAllByNameContaining(String name);

    public Optional<Member> findByEmail(String email);

    public List<Member> findAllByEmailContaining(String email);

    public Optional<Member> findByPhone(String phone);

    public List<Member> findAllByPhoneLike(String phone);

    // balance: không cần tìm
    public List<Member> findAllByBalance(float balance);

    public List<Member> findAllByBalanceBetween(float start, float end);

    public List<Member> findAllByMajorId(int id);

    public MemberModel getModel(int id);
    
    public MemberModel getModel(String email);

    public List<MemberDTO> getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(String input);

    public List<MemberDTO> getAllByNameContaining(String name);

    public List<MemberDTO> getAllByTeamId(int id);

    public boolean isExistedInMember(int skillId, int memberId);

	public boolean update(MemberUpdateModel m);

	public boolean updateDescription(MemberUpdateDescriptionModel m);

	public boolean updateSkill(MemberUpdateSkillModel m, Member member);

}
