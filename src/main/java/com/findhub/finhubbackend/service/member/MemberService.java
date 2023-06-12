package com.findhub.finhubbackend.service.member;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
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

    public List<Member> findAllByMajorCodeStartingWith(String majorCode);
}
