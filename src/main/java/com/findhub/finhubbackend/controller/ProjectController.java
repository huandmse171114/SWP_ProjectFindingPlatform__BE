package com.findhub.finhubbackend.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.model.ProjectCreateModel;
import com.findhub.finhubbackend.model.ProjectResponseModel;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.util.Config.*;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

	@GetMapping("/v2/" + ApiPath.ID)
	public ResponseEntity<ProjectResponseModel> getProject(@PathVariable(Var.ID) int id) {
		Project project = service.findById(id);

		Map<Integer, String> skills = new HashMap<>();
		for (Skill skill : project.getSkillSet())
			skills.put(skill.getId(), skill.getName());

		ProjectResponseModel prm = ProjectResponseModel.builder()
				.id(id)
				.name(project.getTitle())
				.description(project.getDescription())
				.skills(skills)
				.publishDate(project.getPublishDate())
				.deliverDays(project.getDeliverDays())
				.wage(project.getWage())
				.dueDate(new Date(project.getPublishDate().getTime() + 1000 * 60 * 60 * 24 * project.getDeliverDays()))
				.category(project.getType())
				.status(ProjectStatus.nameOf(project.getStatus()))
				.build();

		return new ResponseEntity<ProjectResponseModel>(prm, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<String> add(@RequestBody ProjectCreateModel model) {
		long miliSecond = System.currentTimeMillis();

		Project project = Project.builder()
				.title(model.getTitle())
				.type(model.getType())
				.description(model.getDescription())
				.wage(model.getWage())
				.imageURL("media/projects/" + model.getImageFile().getOriginalFilename() + miliSecond)
				.deliverDays(model.getDeliverDays())
				.publishDate(model.getPublishDate())
				.build();

		service.save(project);
		return new ResponseEntity<>("Added new Project successfully", HttpStatus.OK);
	}

}
