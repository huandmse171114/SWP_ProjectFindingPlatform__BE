package com.findhub.finhubbackend.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.model.ProjectCreateModel;
import com.findhub.finhubbackend.model.ProjectResponseModel;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

	@GetMapping("/v2/" + ApiPath.ID)
	public ResponseEntity<ProjectResponseModel> getProject(@PathVariable(Var.ID) int id) {
		Project project = service.findById(id);

		if (project == null)
			return null;

		String status = ProjectStatus.nameOf(project.getStatus());
		Date dueDate = Utils.addDate(project.getPublishDate(), project.getDeliverDays());

		Map<Integer, String> skills = new HashMap<>();
		for (var s : project.getSkillSet())
			skills.put(s.getId(), s.getName());

		Map<Integer, String> categories = new HashMap<>();
		for (var c : project.getCategorySet())
			categories.put(c.getId(), c.getName());

		Map<Integer, String> deliverableTypes = new HashMap<>();
		// for (var dt : project.getDeliverableTypeSet())
		// 	deliverableTypes.put(dt.getId(), dt.getName());

		return new ResponseEntity<ProjectResponseModel>(
				ProjectResponseModel.builder()
						.id(id)
						.name(project.getName())
						.description(project.getDescription())
						.delivarableTypes(deliverableTypes)
						.skills(skills)
						.publishDate(project.getPublishDate())
						.deliverDays(project.getDeliverDays())
						.wage(project.getWage())
						.dueDate(dueDate)
						.categories(categories)
						.status(status)
						.build(),
				HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<String> add(@RequestBody ProjectCreateModel model) {

		Project project = Project.builder()
				.name(model.getName())
				// .deliverableTypeSet(model.getDeliverableTypeSet())
				.description(model.getDescription())
				.wage(model.getWage())
				.imageURL(model.getImageURL())
				.deliverDays(model.getDeliverDays())
				.publishDate(model.getPublishDate())
				.build();

		service.save(project);
		return new ResponseEntity<>("Added new Project successfully", HttpStatus.OK);
	}

}
