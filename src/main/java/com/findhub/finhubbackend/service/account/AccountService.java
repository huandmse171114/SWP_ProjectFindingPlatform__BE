package com.findhub.finhubbackend.service.account;

import java.util.List;

import com.findhub.finhubbackend.entity.account.Account;

public interface AccountService {

	public Account add(Account account);

	public Account update(int id, Account account);

	public Account update(Account oldAccount, Account newAccount);

	public boolean changeStatus(int id, int status);

	public boolean changeStatus(Account account, int status);

	public boolean delete(int id);

	public boolean delete(Account account);

	public Account findById(int id);

	public Account findByEmail(String email);

	public List<Account> getAll();

	public List<Account> findByIdLike(int id);

	public List<Account> findByEmailLike(String email);

	public List<Account> findByStatus(int status);

	public List<Account> findByRole(int role);

	public void save(Account account);
}
