package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.model.AccountRegisterModel;
import com.findhub.finhubbackend.security.CustomUserDetailService;
import com.findhub.finhubbackend.service.account.AccountService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody AccountRegisterModel accountModel) {
		if (userDetailService.existsByEmail(accountModel.getEmail())) {
			return new ResponseEntity<>("Response is taken!", HttpStatus.BAD_REQUEST);
		}else {
			Account account = Account.builder()
					.email(accountModel.getEmail())
					.password(passwordEncoder.encode(accountModel.getPassword()))
					.role(accountModel.getRole())
					.status(1)
					.build();
			
			accountService.saveAccount(account);
			
			return new ResponseEntity<>("Register success", HttpStatus.OK);
		}
	}
}
