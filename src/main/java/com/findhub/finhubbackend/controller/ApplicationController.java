package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.entity.teamProject.TeamProject;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.ApplicationCreateModel;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.service.teamProject.TeamProjectService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.APPLICATION)
public class ApplicationController
		extends ApiController<Application, ApplicationService, ApplicationStatus> {

	@Autowired
	private TeamService teamService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TeamProjectService tpService;

	@PostMapping()
	// @Override
	public ResponseEntity<String> create(@RequestBody ApplicationCreateModel model) {
		int teamId = model.getTeamId();
		int projectId = model.getProjectId();
		// int leaderId = model.getLeaderId();

		if (service.existsByTeamIdAndProjectId(teamId, projectId))
			return new ResponseEntity<String>(
					"Failed to add "
							+ "Application[team=" + teamId + "; project=" + projectId + "]: "
							+ "already existed",
					HttpStatus.FOUND);

		Application application = Application
				.builder()
				.team(
						teamService.get(teamId))
				.project(
						projectService.get(projectId))
				.message(model.getMessage())
				.build();

		service.save(application);
		return new ResponseEntity<String>(
				"Added new Application[teamId=" + teamId + "; projectId=" + projectId + "] successfully",
				HttpStatus.OK);
	}

	@PostMapping("/approved/{applicationId}")
	public ResponseEntity<?> approved(@PathVariable int applicationId) {
		Application a = service.get(applicationId);
		if (a == null)
			throw new EntityNotFoundException(Application.class, applicationId);

		Team t = a.getTeam();
		Project p = a.getProject();

		t.setStatus(TeamStatus.ONGOING.getValue());
		teamService.update(t);
		
		p.setStatus(ProjectStatus.ONGOING.getValue());
		projectService.update(p);
		
		a.setStatus(ApplicationStatus.APPROVED.getValue());
		service.update(a);
		
		tpService.save(
				TeamProject
						.builder()
						.team(t)
						.project(p)
						.build());

		return response("Application approved", HttpStatus.OK);
	}

	@PostMapping("/rejected/{applicationId}")
	public ResponseEntity<?> rejected(@PathVariable int applicationId) {
		Application a = service.get(applicationId);
		if (a == null)
			throw new EntityNotFoundException(Application.class, applicationId);

		 Team t = a.getTeam();
		 Project p = a.getProject();

		a.setStatus(ApplicationStatus.REJECTED.getValue());
		service.update(a);
		 tpService.save(
		 		TeamProject
		 				.builder()
		 				.team(t)
		 				.project(p)
		 				.build());

		return response("Application rejected", HttpStatus.OK);
	}
}
