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
import com.findhub.finhubbackend.model.model.MemberModel;
import com.findhub.finhubbackend.model.model.SkillModel;
import com.findhub.finhubbackend.model.response.MajorResponseModel;
import com.findhub.finhubbackend.repository.MemberRepository;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.service.service.ServiceImpl;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Utils;

@Service
public class MemberServiceImpl extends ServiceImpl<Member, MemberRepository, MemberStatus>
        implements MemberService {

    @Autowired
    private SkillService skillService;

    @Autowired
    private MajorService majorService;

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

        if (member == null) return null;

        List<SkillModel> skills = new ArrayList<>();
        skillService.getNameAndLevelByMemberId(id)
            .forEach(each -> skills.add(
                SkillModel
                    .builder()
                        .id(each.getId())
                        .name(each.getName())
                        .level(each.getLevel())
                    .build()
            )
        );

        String status = Utils.capitalize(
            MemberStatus.nameOf(
                member.getStatus()
            )
        );
        Major major = majorService.get(member.getMajorId());

        return MemberModel
            .builder()
                .id(id)
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .description(member.getDescription())
                .balance(member.getBalance())
                .DOB(member.getDob().toString())
                .major(MajorResponseModel
                    .builder()
                        .id(major.getId())
                        .code(major.getCode())
                        .name(major.getName())
                    .build()
                )
                .skills(skills)
                .status(status)
            .build();
    }

}
