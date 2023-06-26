package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.account.Account;

// @Repository
public interface AccountRepository extends Repo<Account> {

	Optional<Account> findByEmail(String email);

	List<Account> findAllByEmailContaining(String email);

	List<Account> findAllByRole(int role);

	boolean existsByEmail(String email);

}