package com.findhub.finhubbackend.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
}
