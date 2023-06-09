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
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping(Path.ALL)
	public List<Account> getAccounts() {
		return accountService.getAll();
	}

	@GetMapping(Path.ID)
	public Account getAccountById(@PathVariable(Var.ID) int id) {
		return accountService.findById(id);
	}

	public Account addAccount(Account account) {
		return accountService.add(account);
	}

	@PostMapping(Path.UPDATE)
	public Account updateAccount(@PathVariable(Var.ID) int id, @RequestBody Account account) {
		return accountService.update(id, account);
	}

	@PostMapping(Path.DELETE)
	public boolean deleteAccount(@RequestBody int id) {
		return accountService.changeStatus(id, AccountStatus.INACTIVE);
	}

	@PostMapping(Path.RESTORE)
	public boolean restoreAccount(@RequestBody int id) {
		return accountService.changeStatus(id, AccountStatus.ACTIVE);
	}
}
