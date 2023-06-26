package com.findhub.finhubbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.dto.TeamDTO;
import com.findhub.finhubbackend.entity.team.Team;

// @Repository
public interface TeamRepository extends Repo<Team> {

    List<Team> findAllById(int id);

    // balance: không cần tìm
    List<Team> findAllByBalance(float balance);

    List<Team> findAllByBalanceBetween(float start, float end);

    // List<Team> findAllByNameContaining(String name);

    @Query(value = """
            SELECT *
            FROM
                Team
            WHERE
                Name LIKE %:name%
            OR
                Id Like :id%
            ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<TeamDTO> getAllByIdStartingWithOrNameContaining(
            @Param("id") int id,
            @Param("name") String name);

    @Query(value = """
            SELECT *
            FROM
                Team
            WHERE
                Name LIKE %:name%
            ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<TeamDTO> getAllByNameContaining(@Param("name") String name);

}
