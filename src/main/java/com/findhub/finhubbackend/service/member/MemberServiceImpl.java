package com.findhub.finhubbackend.service.member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.repository.MemberRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class MemberServiceImpl extends ServiceImpl<Member, MemberRepository, MemberStatus>
        implements MemberService {

    @Override
    public List<Member> findAllByBalance(float balance) {
        return repo.findAllByBalance(balance);
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
    public List<Member> findAllByMajorCodeStartingWith(String majorCode) {
        return repo.findAllByMajorCodeStartingWith(majorCode);
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

}
