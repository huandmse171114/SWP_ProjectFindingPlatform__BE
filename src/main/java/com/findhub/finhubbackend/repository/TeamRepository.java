package com.findhub.finhubbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.team.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>, Repo<Team> {

    List<Team> findAllById(int id);

    // balance: không cần tìm
    List<Team> findAllByBalance(float balance);

    List<Team> findAllByBalanceBetween(float start, float end);

}
