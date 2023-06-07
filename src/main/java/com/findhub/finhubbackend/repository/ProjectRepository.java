package com.findhub.finhubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.project.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
