package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MEMBER)
public class MemberController extends ApiController<Member, MemberService, MemberStatus> {

	// @PostMapping(ApiPath.ENABLE)
	// public boolean enableEntity(@RequestBody int id) {
	// 	return service.updateStatus(id, MemberStatus.AVAILABLE);
	// }

	// @PostMapping(ApiPath.DISABLE)
	// public boolean disableEntity(@RequestBody int id) {
	// 	return service.updateStatus(id, MemberStatus.DELETED);
	// }

	@PostMapping("/")
	public ResponseEntity<String> add(@RequestBody int sth) {
		return new ResponseEntity<String>("not support yet", HttpStatus.OK);
	}
}
