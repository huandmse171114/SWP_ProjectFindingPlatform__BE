package com.findhub.finhubbackend.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.application.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>, Repo<Application> {

    /**
     * tìm tất cả category có ProjectId chính xác
     */
    List<Application> findAllByProjectId(int projectId);

    /**
     * tìm tất cả category có ProjectId gần đúng
     */
    List<Application> findAllByProjectIdStartingWith(int projectId);

    /**
     * tìm tất cả category có TeamId chính xác
     */
    List<Application> findAllByTeamId(int teamId);

    /**
     * tìm tất cả category có TeamId gần đúng
     */
    List<Application> findAllByTeamIdStartingWith(int teamId);

    /**
     * tìm tất cả category theo date
     */
    List<Application> findAllByCreateAt(Date date);

    /**
     * tìm tất cả category theo date before
     */
    List<Application> findAllByCreateAtBefore(Date date);

    /**
     * tìm tất cả category theo date after
     */
    List<Application> findAllByCreateAtAfter(Date date);

    /**
     * tìm tất cả category từ date tới date
     */
    List<Application> findAllByCreateAtBetween(Date startDate, Date endDate);

    boolean existsByTeamId(String teamId);

    boolean existsByTeamIdAndProjectId(int teamId, int projectId);

}
