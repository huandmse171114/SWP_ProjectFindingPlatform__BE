package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

	public List<ProjectResponseModel> findAllByIdContaining(int id) {
		List<ProjectResponseModel> result = new ArrayList<>();
		List<Project> similarId = service.findAllByIdContaining(id);
		similarId.forEach(each -> result.add(service.getResponseModelById(each.getId())));
		return result;
	}

	public List<ProjectResponseModel> findAllByNameContaining(String name) {
		List<ProjectResponseModel> result = new ArrayList<>();

		service.findAllByNameContaining(name)
				.forEach(each -> result.add(
						service.getResponseModelById(each.getId())));

		return result;
	}

	public List<ProjectResponseModel> getAllByNameContainingOrIdLike(String input) {
		int id = Integer.parseInt(input);
		List<ProjectResponseModel> result = new ArrayList<>();

		service.getAllByNameContainingOrIdLike(id, input)
				.forEach(each -> result.add(
						service.getResponseModelById(each.getId())));

		return result;
	}

	@Override
	public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
		ProjectResponseModel p = service.getResponseModelById(id);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAll() {
		List<Project> projects = service.getAll();

		if (projects.isEmpty())
			return new ResponseEntity<>(projects, HttpStatus.OK);

		List<ProjectResponseModel> prm = new ArrayList<>();
		for (var p : projects)
			prm.add(
					service.getResponseModelById(p.getId()));
		return new ResponseEntity<>(prm, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> add(@RequestBody ProjectCreateModel model) {

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
