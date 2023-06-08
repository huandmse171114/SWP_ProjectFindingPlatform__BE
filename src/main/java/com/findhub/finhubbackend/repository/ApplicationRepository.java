package com.findhub.finhubbackend.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.application.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query(value = "SELECT a FROM Application a WHERE ProjectId = ?1")
    List<Application> findAccountsByProjectId(int projectId);

    @Query(value = "SELECT a FROM Account a WHERE TeamId = ?1")
    List<Application> findAccountsByTeamId(int teamId);

    @Query(value = "SELECT a FROM Application a WHERE Status = ?1")
    List<Application> findAccountsByStatus(int status);

    @Query(value = "SELECT a FROM Application a WHERE CreateAt = ?1")
    List<Application> findAccountsByDate(Date date);

}
