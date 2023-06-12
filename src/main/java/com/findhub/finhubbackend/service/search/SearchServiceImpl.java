package com.findhub.finhubbackend.service.search;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.repository.AccountRepository;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findAccountById(int id) {
		Optional<Account> accountOptional = accountRepository.findById(id);
		if (accountOptional.isPresent()) return accountOptional.get();
		else return null;
	}
	
	

}
