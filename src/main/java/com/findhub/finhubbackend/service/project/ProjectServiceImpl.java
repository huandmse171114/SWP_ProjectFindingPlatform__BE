package com.findhub.finhubbackend.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectRepository projectRepository;
	
}
