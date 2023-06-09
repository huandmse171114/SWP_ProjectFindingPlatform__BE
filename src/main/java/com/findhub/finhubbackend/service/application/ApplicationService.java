package com.findhub.finhubbackend.service.application;

import java.sql.Date;
import java.util.List;

import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;

public interface ApplicationService {
    /**
     * add new Application to DB
     */
    public Application add(Application application);

    /**
     * update Application
     */
    public Application update(int id, Application application);

    /**
     * update Application
     */
    public Application update(Application oldApplication, Application newApplication);

    /**
     * set new status of Application
     */
    public boolean changeStatus(int id, int status);

    /**
     * set new status of Application
     */
    public boolean changeStatus(int id, ApplicationStatus status);

    /**
     * set new status of Application
     */
    public boolean changeStatus(Application application, int status);

    /**
     * set new status of Application
     */
    public boolean changeStatus(Application application, ApplicationStatus status);

    /**
     * delete existed Application from DB
     */
    public boolean delete(int id);

    /**
     * delete existed Application from DB
     */
    public boolean delete(Application application);

    /**
     * find application by id (exact id)
     */
    public Application findById(int id);

    /**
     * get all applications in DB
     */
    public List<Application> getAll();

    /**
     * tìm tất cả application có id gần đúng
     */
    public List<Application> findAllByIdLike(int id);

    /**
     * tìm tất cả application có ProjectId chính xác
     */
    public List<Application> findAllByProjectId(int projectId);

    /**
     * tìm tất cả application có ProjectId gần đúng
     */
    public List<Application> findAllByProjectIdLike(int projectId);

    /**
     * tìm tất cả application có TeamId chính xác
     */
    public List<Application> findAllByTeamId(int teamId);

    /**
     * tìm tất cả application có TeamId gần đúng
     */
    public List<Application> findAllByTeamIdLike(int teamId);

    /**
     * tìm tất cả application với status
     */
    public List<Application> findAllByStatus(int status);

    /**
     * tìm tất cả application với status
     */
    public List<Application> findAllByStatus(ApplicationStatus status);

    /**
     * tìm tất cả application theo date
     */
    public List<Application> findAllByDate(Date createDate);

    /**
     * tìm tất cả application từ date tới date
     */
    public List<Application> findAllByDateBetween(Date startDate, Date endDate);

    /**
     * save application
     */
    public void save(Application application);
}
