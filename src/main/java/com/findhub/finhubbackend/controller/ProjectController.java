package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

  // @PostMapping(apiPath.ENABLE)
	// public boolean enableEntity(@RequestBody int id) {
	// return service.changeStatus(id, ProjectStatus.ONGOING);
	// }

	// @PostMapping(apiPath.DISABLE)
	// public boolean disableEntity(@RequestBody int id) {
	// return service.changeStatus(id, ProjectStatus.DELETED);
	// }
}

