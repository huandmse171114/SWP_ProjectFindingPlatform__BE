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
    /**
     * tìm chính xác application
     */
    Optional<Application> findById(int id);

    /**
     * tìm tất cả application có id gần đúng
     */
    List<Application> findAllByIdLike(int id);

    /**
     * tìm tất cả application có ProjectId chính xác
     */
    List<Application> findAllByProjectId(int projectId);

    /**
     * tìm tất cả application có ProjectId gần đúng
     */
    List<Application> findAllByProjectIdLike(int projectId);

    /**
     * tìm tất cả application có TeamId chính xác
     */
    List<Application> findAllByTeamId(int teamId);

    /**
     * tìm tất cả application có TeamId gần đúng
     */
    List<Application> findAllByTeamIdLike(int teamId);

    /**
     * tìm tất cả application với status
     */
    List<Application> findAllByStatus(int status);

    /**
     * tìm tất cả application theo date
     */
    List<Application> findAllByCreateAt(Date date);

    /**
     * tìm tất cả application từ date tới date
     */
    List<Application> findAllByCreateAtBetween(Date startDate, Date endDate);

}
