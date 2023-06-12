package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.ACCOUNT)
public class AccountController extends MyController<Account, AccountService, AccountStatus> {

	@PostMapping(ApiPath.ENABLE)
	public boolean enableEntity(@RequestBody int id) {
		return service.changeStatus(id, AccountStatus.ACTIVE);
	}

	@PostMapping(ApiPath.DISABLE)
	public boolean disableEntity(@RequestBody int id) {
		return service.changeStatus(id, AccountStatus.INACTIVE);
	}
}
