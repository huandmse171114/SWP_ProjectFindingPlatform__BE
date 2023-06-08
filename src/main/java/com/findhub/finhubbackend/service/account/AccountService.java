package com.findhub.finhubbackend.service.account;

import java.util.List;

import com.findhub.finhubbackend.entity.account.Account;

public interface AccountService {

	public Account add(Account account);

	public Account update(int id, Account account);

	/**
	 * status = ACTIVE -> INACTIVE
	 * <p>
	 * status = INACTIVE -> ACTIVE
	 */
	public boolean changeStatus(int id, int status);

	public boolean delete(int id);

	public boolean delete(Account account);

	public List<Account> getAll();

	public Account findById(int id);

	public List<Account> findAccountsByEmail(String email);

	public List<Account> findAccountsByStatus(int status);

	public List<Account> findAccountsByRole(int role);

	public void save(Account account);
}
