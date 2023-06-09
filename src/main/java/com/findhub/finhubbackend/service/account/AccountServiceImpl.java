package com.findhub.finhubbackend.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account add(Account account) {
		return (account != null) ? accountRepository.save(account) : null;
	}

	@Override
	public boolean changeStatus(Account account, int status) {
		if (account != null) {
			account.setStatus(status);
			return true;
		}
		return false;
	}

	@Override
	public boolean changeStatus(int id, int status) {
		Optional<Account> account = accountRepository.findById(id);
		return changeStatus(account.isPresent() ? account.get() : null, status);
	}

	@Override
	public boolean delete(Account account) {
		if (account != null) {
			accountRepository.delete(account);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		Optional<Account> account = accountRepository.findById(id);
		return delete(account.isPresent() ? account.get() : null);
	}

	@Override
	public Account update(Account oldAccount, Account newAccount) {
		if (newAccount != null && oldAccount != null) {
			int id = oldAccount.getId();
			newAccount.setId(id);

			return accountRepository.save(newAccount);
		}
		return null;
	}

	@Override
	public Account update(int id, Account account) {
		Optional<Account> old = accountRepository.findById(id);
		return update(old.isPresent() ? old.get() : null, account);
	}

	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		return account.isPresent() ? account.get() : null;
	}

	@Override
	public Account findByEmail(String email) {
		Optional<Account> account = accountRepository.findByEmail(email);
		return account.isPresent() ? account.get() : null;
	}

	@Override
	public List<Account> findAllByIdLike(int id) {
		return accountRepository.findAllByIdLike(id);
	}

	@Override
	public List<Account> findAllByEmailLike(String email) {
		return accountRepository.findAllByEmailLike(email);
	}

	@Override
	public List<Account> findAllByRole(AccountRole role) {
		return findAllByRole(role.getValue());
	}

	@Override
	public List<Account> findAllByRole(int role) {
		return accountRepository.findAllByRole(role);
	}

	@Override
	public List<Account> findAllByStatus(AccountStatus status) {
		return findAllByStatus(status.getValue());
	}

	@Override
	public List<Account> findAllByStatus(int status) {
		return accountRepository.findByStatus(status);
	}

	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}
}
