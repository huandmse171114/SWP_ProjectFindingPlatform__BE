package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.service.member.MemberService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/members")
public class MemberController {

	@Autowired
	private MemberService memberService;
}
