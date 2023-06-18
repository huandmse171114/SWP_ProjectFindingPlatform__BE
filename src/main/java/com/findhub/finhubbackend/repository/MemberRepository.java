package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>, Repo<Member> {

    /**
     * tìm chính xác name
     */
    Optional<Member> findByName(String name);

    /**
     * tìm tất cả Member có chính xác
     */
    List<Member> findAllByNameContaining(String name);

    Optional<Member> findByEmail(String email);

    List<Member> findAllByEmailContaining(String email);

    Optional<Member> findByPhone(String phone);

    List<Member> findAllByPhoneLike(String phone);

    // balance: không cần tìm
    List<Member> findAllByBalance(float balance);

    List<Member> findAllByBalanceBetween(float start, float end);

    List<Member> findAllByMajorId(int id);

    List<Member> findAllByStatus(int status);

    boolean existsByEmail(String email);
}
