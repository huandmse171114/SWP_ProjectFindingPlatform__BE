package com.findhub.finhubbackend.service.major;

import java.util.List;

import com.findhub.finhubbackend.entity.major.Major;

public interface MajorService {
    public Major add(Major application);

    public Major update(int id, Major application);

    /**
     * status = ACTIVE -> INACTIVE
     * <p>
     * status = INACTIVE -> ACTIVE
     */
    public boolean changeStatus(int id);

    public boolean delete(int id);

    public void delete(Major major);

    public List<Major> getAll();

    public Major findById(int id);

    public List<Major> findApplicationsByProjectId(int projectId);

    public List<Major> findApplicationsByTeamId(int teamId);

    public List<Major> findApplicationsByStatus(int status);

    // public List<Major> findApplicationsByDate(Date date);

    public void saveApplication(Major application);
}
