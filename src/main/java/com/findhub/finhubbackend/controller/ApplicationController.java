package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.model.create.ApplicationCreateModel;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.APPLICATION)
public class ApplicationController extends ApiController<Application, ApplicationService, ApplicationStatus> {

	// @Autowired
	// private TeamService teamService;

	// @PostMapping("/")
	@Override
	public ResponseEntity<String> create(@RequestBody Object model) {
		ApplicationCreateModel m = (ApplicationCreateModel) model;
		int teamId = m.getTeamId();
		int projectId = m.getProjectId();
		// int leaderId = m.getLeaderId();

		if (service.existsByTeamIdAndProjectId(teamId, projectId))
			return new ResponseEntity<String>(
					"Failed to add "
							+ "Application[team=" + teamId + "; project=" + projectId + "]: "
							+ "already existed",
					HttpStatus.FOUND);

		Application application = Application.builder()
				.teamId(teamId)
				.projectId(projectId)
				.build();

		service.save(application);
		return new ResponseEntity<String>(
				"Added new Application[teamId=" + teamId + "; projectId=" + projectId + "] successfully",
				HttpStatus.OK);
	}
}
