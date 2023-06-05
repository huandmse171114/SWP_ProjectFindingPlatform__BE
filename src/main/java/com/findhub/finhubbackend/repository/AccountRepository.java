package com.findhub.finhubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

}
