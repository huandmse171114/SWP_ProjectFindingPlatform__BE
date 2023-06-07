package com.findhub.finhubbackend.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account findAccountById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		
		if (account.isPresent()) {
			return account.get();
		}else {
			return null;
		}
	}

	@Override
	public void saveAccount(Account account) {
		accountRepository.save(account);
	}
}
