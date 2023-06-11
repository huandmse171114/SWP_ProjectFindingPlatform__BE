package com.findhub.finhubbackend.service.account;

import java.util.List;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface AccountService extends Service<Account, AccountStatus> {
	/**
	 * find account by email (exact Email)
	 */
	public Account findByEmail(String email);

	/**
	 * find accounts by id (approximate id)
	 */
	public List<Account> findAllByIdStartingWith(int id);

	/**
	 * find accounts by email (approximate Email)
	 */
	public List<Account> findAllByEmailContaining(String email);

	/**
	 * find accounts by role
	 */
	public List<Account> findAllByRole(int role);

	/**
	 * find accounts by role
	 */
	public List<Account> findAllByRole(AccountRole role);
}
