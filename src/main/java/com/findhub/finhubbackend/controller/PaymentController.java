package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.payment.Payment;
import com.findhub.finhubbackend.entity.payment.PaymentStatus;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.model.create.PaymentCreateModel;
import com.findhub.finhubbackend.service.payment.PaymentService;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PAYMENT)
public class PaymentController extends ApiController<Payment, PaymentService, PaymentStatus> {

	@Autowired
	private ProjectService projectService;

	// @Autowired
	// private AccountService accountService;

	@Autowired
	private TeamService teamService;

	@PostMapping("/")
	public ResponseEntity<String> add(@RequestBody PaymentCreateModel model) {

		int projectId = model.getProjectId();
		Project project = projectService.get(projectId);
		if (project == null)
			return new ResponseEntity<String>(
				"Failed to create Payment:"
						+ " Project[id=" + projectId + "] not existed",
				HttpStatus.BAD_REQUEST
			);

		int teamId = model.getTeamId();
		Team team = teamService.get(teamId);
		if (team == null)
			return new ResponseEntity<String>(
				"Failed to create Payment:"
						+ " Team[id=" + teamId + "] not existed",
				HttpStatus.BAD_REQUEST
			);

		// if (!projectService.existsByTeamIdAndProjectId(teamId, projectId))
		// return new ResponseEntity<String>(
		// "Failed to create Payment"
		// + "Project[id=" + projectId + "] does not have Team[id=" + teamId + "]",
		// HttpStatus.BAD_REQUEST);

		Payment payment = Payment
			.builder()
				.projectId(projectId)
				.teamId(teamId)
				.amount(model.getAmount())
				.description(model.getDescription())
			.build();

		service.save(payment);
		return new ResponseEntity<String>("not support yet", HttpStatus.OK);
	}
}
