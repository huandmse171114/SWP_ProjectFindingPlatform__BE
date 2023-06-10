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
public class ApplicationController extends MyController<Application, ApplicationService, ApplicationStatus> {

	@PostMapping(Path.ENABLE)
	public boolean enableEntity(@RequestBody int id) {
		return service.changeStatus(id, ApplicationStatus.PENDING);
	}

	@PostMapping(Path.DISABLE)
	public boolean disableEntity(@RequestBody int id) {
		return service.changeStatus(id, ApplicationStatus.DELETED);
	}

	public boolean changeStatusEntity(@RequestBody int id, @RequestBody int status) {
		return service.changeStatus(id, status);
	}
}
