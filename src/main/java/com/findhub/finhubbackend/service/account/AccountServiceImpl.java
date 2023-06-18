package com.findhub.finhubbackend.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.repository.AccountRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class AccountServiceImpl extends ServiceImpl<Account, AccountRepository, AccountStatus>
		implements AccountService {

	@Override
	public Account findByEmail(String email) {
		Optional<Account> account = repo.findByEmail(email);
		return account.isPresent() ? account.get() : null;
	}

	@Override
	public List<Account> findAllByEmailContaining(String email) {
		return repo.findAllByEmailContaining(email);
	}

	@Override
	public List<Account> findAllByRole(AccountRole role) {
		return findAllByRole(role.getValue());
	}

	@Override
	public List<Account> findAllByRole(int role) {
		return repo.findAllByRole(role);
	}

}
