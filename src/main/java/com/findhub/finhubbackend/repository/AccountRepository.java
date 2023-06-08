package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	// public Optional<Account> findById(String id);

	Optional<Account> findByEmail(String email);

	@Query(value = "SELECT a FROM Account a WHERE Email LIKE %?1%")
	List<Account> findAccountsByEmail(String email);

	@Query(value = "SELECT a FROM Account a WHERE Status = ?1")
	List<Account> findAccountsByStatus(int status);

	@Query(value = "SELECT a FROM Account a WHERE Role = ?1")
	List<Account> findAccountsByRole(int role);

	public Boolean existsByEmail(String email);
	public Optional<Account> findByStatus(int status);
	
}
