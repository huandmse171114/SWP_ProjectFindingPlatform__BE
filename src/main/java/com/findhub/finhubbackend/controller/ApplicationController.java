package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.util.Config.*;

@RestController
@CrossOrigin
@RequestMapping(path = Path.APPLICATION)
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping(Path.ALL)
	public List<Application> getAccounts() {
		return applicationService.getAll();
	}

	@GetMapping(Path.ID)
	public Application getAccountById(@PathVariable("id") int id) {
		return applicationService.findById(id);
	}

	public Application addApplication(Application application) {
		return applicationService.add(application);
	}

	@PostMapping(Path.UPDATE)
	public Application updateApplication(@PathVariable(Var.ID) int id, @RequestBody Application application) {
		return applicationService.update(id, application);
	}

	@PostMapping(Path.DELETE)
	public boolean deleteApplication(@RequestBody int id) {
		return applicationService.changeStatus(id, ApplicationStatus.DELETED);
	}

	@PostMapping(Path.RESTORE)
	public boolean restoreApplication(@RequestBody int id) {
		return applicationService.changeStatus(id, ApplicationStatus.PENDING);
	}

	public boolean changeStatusApplication(@RequestBody int id, @RequestBody int status) {
		return applicationService.changeStatus(id, status);
	}
}
