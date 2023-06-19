package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.model.AccountLoginModel;
import com.findhub.finhubbackend.model.AccountRegisterModel;
import com.findhub.finhubbackend.model.AuthResponseModel;
import com.findhub.finhubbackend.security.CustomUserDetailService;
import com.findhub.finhubbackend.security.JwtTokenProvider;
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

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody AccountRegisterModel accountModel) {
		if (userDetailService.existsByEmail(accountModel.getEmail())) {
			return new ResponseEntity<>("Email exist!", HttpStatus.BAD_REQUEST);
		} else {
			Account account = Account.builder()
					.email(accountModel.getEmail())
					.password(passwordEncoder.encode(accountModel.getPassword()))
					.role(accountModel.getRole())
					.status(AccountStatus.ACTIVE.getValue())
					.build();

			accountService.save(account);

			return new ResponseEntity<>("Register success", HttpStatus.OK);
		}
	}

	@PostMapping("login")
	public ResponseEntity<AuthResponseModel> login(@RequestBody AccountLoginModel accountModel) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						accountModel.getEmail(),
						accountModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken(authentication);
		return new ResponseEntity<AuthResponseModel>(new AuthResponseModel(token), HttpStatus.OK);
	}
}
