package com.findhub.finhubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
