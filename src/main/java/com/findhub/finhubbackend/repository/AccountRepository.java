package com.findhub.finhubbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	public Optional<Account> findByEmail(String email);
	public Boolean existsByEmail(String email);
	public Optional<Account> findByStatus(int status);
	
}
