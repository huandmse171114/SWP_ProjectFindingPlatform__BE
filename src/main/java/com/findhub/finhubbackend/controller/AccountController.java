package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.service.account.AccountService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/getAll")
	public List<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable("id") int id) {
		return accountService.findAccountById(id);
	}

	// @GetMapping("/{email}")
	public List<Account> getAccountByEmail(@PathVariable("email") String email) {
		return accountService.findAccountsByEmail(email);
	}

	// @GetMapping("/{status}")
	public List<Account> getAccountByStatus(@PathVariable("status") int status) {
		return accountService.findAccountsByStatus(status);
	}

	// @GetMapping("/{role}")
	public List<Account> getAccountByRole(@PathVariable("role") int role) {
		return accountService.findAccountsByRole(role);
	}
}
