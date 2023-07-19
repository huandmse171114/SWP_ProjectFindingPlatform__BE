package com.findhub.finhubbackend.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.entity.application.Application;

// @Repository
public interface ApplicationRepository extends Repo<Application> {

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
    List<Application> findAllByCreateDate(Date date);

    /**
     * tìm tất cả category theo date before
     */
    List<Application> findAllByCreateDateBefore(Date date);

    /**
     * tìm tất cả category theo date after
     */
    List<Application> findAllByCreateDateAfter(Date date);

    /**
     * tìm tất cả category từ date tới date
     */
    List<Application> findAllByCreateDateBetween(Date startDate, Date endDate);

    boolean existsByTeamId(String teamId);

    boolean existsByTeamIdAndProjectId(int teamId, int projectId);

	List<Application> findAllByProjectIdAndStatus(int id, int value);

}
