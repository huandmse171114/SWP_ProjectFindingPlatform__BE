package com.findhub.finhubbackend.service.account;

import java.util.List;

import com.findhub.finhubbackend.entity.account.Account;

public interface AccountService {

	public List<Account> getAccounts();

	public Account findAccountById(int id);

	public void saveAccount(Account account);

}
