package com.findhub.finhubbackend.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * add new Account to DB
	 */
	@Override
	public Account add(Account account) {
		return (account != null) ? accountRepository.save(account) : null;
	}

	/**
	 * set new status of Account
	 */
	@Override
	public boolean changeStatus(Account account, int status) {
		if (account != null) {
			account.setStatus(status);
			return true;
		}
		return false;
	}

	/**
	 * set new status of Account
	 */
	@Override
	public boolean changeStatus(int id, int status) {
		Optional<Account> account = accountRepository.findById(id);
		return changeStatus(account.get(), status);
	}

	/**
	 * delete existed Account from DB
	 */
	@Override
	public boolean delete(Account account) {
		if (account != null) {
			accountRepository.delete(account);
			return true;
		}
		return false;
	}

	/**
	 * delete existed Account from DB
	 */
	@Override
	public boolean delete(int id) {
		Optional<Account> account = accountRepository.findById(id);
		return delete(account.get());
	}

	/**
	 * update Account
	 */
	@Override
	public Account update(Account oldAccount, Account newAccount) {
		if (newAccount != null && oldAccount != null) {
			int id = oldAccount.getId();
			newAccount.setId(id);

			return accountRepository.save(newAccount);
		}
		return null;
	}

	/**
	 * update Account
	 */
	@Override
	public Account update(int id, Account account) {
		Optional<Account> old = accountRepository.findById(id);
		return update(old.isPresent() ? old.get() : null, account);
	}

	/**
	 * get all accounts in DB
	 */
	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	/**
	 * find account by id (exact id)
	 */
	@Override
	public Account findById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		return account.isPresent() ? account.get() : null;
	}

	/**
	 * find account by email (exact Email)
	 */
	@Override
	public Account findByEmail(String email) {
		Optional<Account> account = accountRepository.findByEmail(email);
		return account.isPresent() ? account.get() : null;
	}

	/**
	 * find accounts by id (approximate id)
	 */
	@Override
	public List<Account> findByIdLike(int id) {
		return accountRepository.findByIdLike(id);
	}

	/**
	 * find accounts by email (approximate Email)
	 */
	@Override
	public List<Account> findByEmailLike(String email) {
		return accountRepository.findByEmailLike(email);
	}

	/**
	 * find accounts by role
	 */
	@Override
	public List<Account> findByRole(int role) {
		return accountRepository.findByRole(role);
	}

	/**
	 * find accounts by status
	 */
	@Override
	public List<Account> findByStatus(int status) {
		return accountRepository.findByStatus(status);
	}

	/**
	 * save account
	 */
	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}
}
