package com.findhub.finhubbackend.service.application;

import java.sql.Date;
import java.util.List;

import com.findhub.finhubbackend.entity.application.Application;

public interface ApplicationService {

    public Application add(Application application);

    public Application update(int id, Application application);

    /**
     * status = ACTIVE -> INACTIVE
     * <p>
     * status = INACTIVE -> ACTIVE
     */
    public boolean changeStatus(int id);

    public boolean delete(int id);

    public boolean delete(Application application);

    public List<Application> getAll();

    public Application findById(int id);

    public List<Application> findApplicationsByProjectId(int projectId);

    public List<Application> findApplicationsByTeamId(int teamId);

    public List<Application> findApplicationsByStatus(int status);

    public List<Application> findApplicationsByDate(Date date);

    public void save(Application application);
}
