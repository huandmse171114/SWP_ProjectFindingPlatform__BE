package com.findhub.finhubbackend.service.account;

import java.util.List;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;

public interface AccountService {
	/**
	 * add new Account to DB
	 */
	public Account add(Account account);

	/**
	 * update Account
	 */
	public Account update(int id, Account account);

	/**
	 * update Account
	 */
	public Account update(Account oldAccount, Account newAccount);

	/**
	 * set new status of Account
	 */
	public boolean changeStatus(int id, int status);

	/**
	 * set new status of Account
	 */
	public boolean changeStatus(Account account, int status);

	/**
	 * delete existed Account from DB
	 */
	public boolean delete(int id);

	/**
	 * delete existed Account from DB
	 */
	public boolean delete(Account account);

	/**
	 * find account by id (exact id)
	 */
	public Account findById(int id);

	/**
	 * find account by email (exact Email)
	 */
	public Account findByEmail(String email);

	/**
	 * get all accounts in DB
	 */
	public List<Account> getAll();

	/**
	 * find accounts by id (approximate id)
	 */
	public List<Account> findAllByIdLike(int id);

	/**
	 * find accounts by email (approximate Email)
	 */
	public List<Account> findAllByEmailLike(String email);

	/**
	 * find accounts by status
	 */
	public List<Account> findAllByStatus(int status);

	/**
	 * find accounts by status
	 */
	public List<Account> findAllByStatus(AccountStatus status);

	/**
	 * find accounts by role
	 */
	public List<Account> findAllByRole(int role);

	/**
	 * find accounts by role
	 */
	public List<Account> findAllByRole(AccountRole role);

	/**
	 * save account
	 */
	public void save(Account account);
}
