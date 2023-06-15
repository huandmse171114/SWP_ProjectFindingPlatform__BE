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

	boolean existsByEmail(String email);

}