package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.APPLICATION)
public class ApplicationController extends ApiController<Application, ApplicationService, ApplicationStatus> {

	@PostMapping(ApiPath.ENABLE)
	public boolean enableEntity(@RequestBody int id) {
		return service.updateStatus(id, ApplicationStatus.PENDING);
	}

	@PostMapping(ApiPath.DISABLE)
	public boolean disableEntity(@RequestBody int id) {
		return service.updateStatus(id, ApplicationStatus.DELETED);
	}
}
