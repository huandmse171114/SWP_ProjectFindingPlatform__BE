package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, Repo<Account> {

	Optional<Account> findByEmail(String email);

	List<Account> findAllByEmailContaining(String email);

	List<Account> findAllByRole(int role);

	public Boolean existsByEmail(String email);

}

// public interface AccountRepository extends JpaRepository<Account, Integer> {

// Optional<Account> findById(int id);

// Optional<Account> findByEmail(String email);

// List<Account> findAllByIdStartingWith(int id);

// List<Account> findAllByEmailContaining(String email);

// List<Account> findAllByStatus(int status);

// List<Account> findAllByRole(int role);

// public Boolean existsByEmail(String email);

// }