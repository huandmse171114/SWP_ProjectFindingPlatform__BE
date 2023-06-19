package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.model.ApplicationCreateModel;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.APPLICATION)
public class ApplicationController extends ApiController<Application, ApplicationService, ApplicationStatus> {

	@Autowired
	private TeamService teamService;

	@PostMapping("/")
	public ResponseEntity<String> add(@RequestBody ApplicationCreateModel model) {
		int teamId = model.getTeamId();
		int projectId = model.getProjectId();
		int leaderId = model.getLeaderId();

		// if(teamService.)

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
