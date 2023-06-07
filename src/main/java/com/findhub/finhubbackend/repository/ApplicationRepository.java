package com.findhub.finhubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.application.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
