package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.findhub.finhubbackend.entity.account.Account;

// @Repository
public interface AccountRepository extends Repo<Account> {

	Optional<Account> findByEmail(String email);

	List<Account> findAllByEmailContaining(String email);

	List<Account> findAllByRole(int role);

	boolean existsByEmail(String email);

	@Query(value = "update Account set Email = :email, Password = :password, Role = :role where Id = :id", nativeQuery = true)
	@Transactional
	@Modifying
	void update(@Param("id") int id, @Param("email") String email, @Param("password") String password, @Param("role") int role);

}