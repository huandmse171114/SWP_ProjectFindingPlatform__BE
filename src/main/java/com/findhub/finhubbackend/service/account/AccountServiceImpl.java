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
	 * add new Account
	 */
	@Override
	public Account add(Account account) {
		return (account != null) ? accountRepository.save(account) : null;
	}

	@Override
	public boolean changeStatus(int id, int status) {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			account.get().setStatus(status);
			return true;
		}
		return false;
	}

	/**
	 * delete Account
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
	 * delete Account
	 */
	@Override
	public boolean delete(int id) {
		Optional<Account> account = accountRepository.findById(id);
		return delete(account.get());
	}

	@Override
	public Account update(int id, Account account) {
		if (account != null) {
			Optional<Account> old = accountRepository.findById(id);
			if (old.isPresent())
				return accountRepository.save(account);
		}
		return null;
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

	@Override
	public List<Account> findAccountsByEmail(String email) {
		return accountRepository.findAccountsByEmail(email);
	}

	@Override
	public List<Account> findAccountsByRole(int role) {
		return accountRepository.findAccountsByRole(role);
	}

	@Override
	public List<Account> findAccountsByStatus(int status) {
		return accountRepository.findAccountsByStatus(status);
	}

	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}
}
