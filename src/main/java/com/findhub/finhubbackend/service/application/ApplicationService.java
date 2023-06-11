package com.findhub.finhubbackend.service.application;

import java.sql.Date;
import java.util.List;

import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface ApplicationService extends Service<Application, ApplicationStatus> {
    /**
     * tìm tất cả application có ProjectId chính xác
     */
    public List<Application> findAllByProjectId(int projectId);

    /**
     * tìm tất cả application có ProjectId gần đúng
     */
    public List<Application> findAllByProjectIdStartingWith(int projectId);

    /**
     * tìm tất cả application có TeamId chính xác
     */
    public List<Application> findAllByTeamId(int teamId);

    /**
     * tìm tất cả application có TeamId gần đúng
     */
    public List<Application> findAllByTeamIdStartingWith(int teamId);

    /**
     * tìm tất cả application theo date
     */
    public List<Application> findAllByDate(Date createDate);

    /**
     * tìm tất cả application theo date
     */
    public List<Application> findAllByDateBefore(Date createDate);

    /**
     * tìm tất cả application theo date after
     */
    public List<Application> findAllByDateAfter(Date createDate);

    /**
     * tìm tất cả application từ date tới date
     */
    public List<Application> findAllByDateBetween(Date startDate, Date endDate);
}
