package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findById(int id);

	Optional<Account> findByEmail(String email);

	List<Account> findByIdLike(int id);

	List<Account> findByEmailLike(String email);

	List<Account> findByStatus(int status);

	List<Account> findByRole(int role);

	public Boolean existsByEmail(String email);

}
