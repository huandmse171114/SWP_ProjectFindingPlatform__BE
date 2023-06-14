package com.findhub.finhubbackend.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.model.ProjectCreateModel;
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

	// @Override
	// @PostMapping(ApiPath.ADD)
	// public ResponseEntity<String> add(@RequestBody ProjectCreateModel projectModel) {
	// 	try {
	// 		byte[] imageData = projectModel.getImageFile().getBytes();

	// 		Project project = Project.builder()
	// 				.title(projectModel.getTitle())
	// 				.type(projectModel.getType())
	// 				.description(projectModel.getDescription())
	// 				.wage(projectModel.getWage())
	// 				.imageURL("imageData")
	// 				.deliverDays(projectModel.getDeliverDays())
	// 				.publishDate(projectModel.getPublishDate())
	// 				.build();

	// 		service.save(project);
	// 	} catch (IOException e) {
	// 		return new ResponseEntity<>("Upload file failed", HttpStatus.BAD_REQUEST);
	// 	}

	// 	return new ResponseEntity<>("Create project successfully", HttpStatus.OK);
	// }

}
