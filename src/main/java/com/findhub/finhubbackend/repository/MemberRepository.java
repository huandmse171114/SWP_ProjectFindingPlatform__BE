package com.findhub.finhubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

}
