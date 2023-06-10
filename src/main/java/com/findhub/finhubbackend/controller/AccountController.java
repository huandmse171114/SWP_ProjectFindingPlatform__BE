package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.util.Config.*;

@RestController
@CrossOrigin
@RequestMapping(path = Path.ACCOUNT)
public class AccountController extends MyController<Account, AccountService, AccountStatus> {

	@PostMapping(Path.ENABLE)
	public boolean enableEntity(@RequestBody int id) {
		return service.changeStatus(id, AccountStatus.ACTIVE);
	}

	@PostMapping(Path.DISABLE)
	public boolean disableEntity(@RequestBody int id) {
		return service.changeStatus(id, AccountStatus.INACTIVE);
	}

	public boolean changeStatusEntity(@RequestBody int id, @RequestBody int status) {
		return service.changeStatus(id, status);
	}
}
