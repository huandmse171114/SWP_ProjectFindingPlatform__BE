package com.findhub.finhubbackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

		List<String> skills = new ArrayList<>();
		
		for (var s : project.getSkillSet())
			skills.add(s.getName());

		List<String> categories = new ArrayList<>();
		for (var c : project.getCategorySet())
			categories.add(c.getName());

		List<String> deliverableTypes = new ArrayList<>();
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
	
	@GetMapping("/v3/all")
	public List<ProjectResponseModel> getAllProject() {
		List<Project> projects = service.getAll();

		if (projects.isEmpty())
			return null;

		Project project;
		List<ProjectResponseModel> retList = new ArrayList<>();
		List<String> skills = new ArrayList<>();
		List<String> categories = new ArrayList<String>();
		
		skills.add("Git");
		skills.add("Figma");
		skills.add("Bash");
		skills.add("C/C++");
		skills.add("Java");
		
		categories.add("WEEBOO");
		
		for (int i = 0; i < projects.size(); i++) {
			project = projects.get(i);
			String status = ProjectStatus.nameOf(project.getStatus());
			Date dueDate = Utils.addDate(project.getPublishDate(), project.getDeliverDays());
//			
//			for (var s : project.getSkillSet())
//				skills.add(s.getName());
//			for (var c : project.getCategorySet())
//				categories.add(c.getName());
			
//			List<String> deliverableTypes = new HashMap<>();
			retList.add(ProjectResponseModel.builder()
						.id(project.getId())
						.name(project.getName())
						.description(project.getDescription())
//						.delivarableTypes(deliverableTypes)
						.skills(skills)
						.publishDate(project.getPublishDate())
						.deliverDays(project.getDeliverDays())
						.wage(project.getWage())
						.dueDate(dueDate)
						.categories(categories)
						.status(status)
						.build());
			
		}
		// for (var dt : project.getDeliverableTypeSet())
		// 	deliverableTypes.put(dt.getId(), dt.getName());

		return retList;
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
