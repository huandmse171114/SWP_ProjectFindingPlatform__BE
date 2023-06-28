package com.findhub.finhubbackend.controller;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

// import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.entity.projectCategoryDetail.ProjectCategoryDetail;
import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.ProjectCreateModel;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.projectCategoryDetail.ProjectCategoryDetailService;
import com.findhub.finhubbackend.service.projectSkillRequire.ProjectSkillRequireService;
import com.findhub.finhubbackend.util.Utils;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

	@Autowired
	private ProjectSkillRequireService psrService;

	@Autowired
	private ProjectCategoryDetailService pcdServie;

	@Override
	public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
		ProjectResponseModel p = service.getById(id);

		if (p == null)
			throw new EntityNotFoundException(entityName, id);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(p);
	}

	@Override
	public ResponseEntity<?> getAll() {
		List<Project> projects = service.getAll();

		if (projects.isEmpty())
			throw new EntityNotFoundException("No projects found");

		List<ProjectResponseModel> prm = new ArrayList<>();
		for (var p : projects)
			prm.add(service.getById(p.getId()));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(prm);
	}

	@PostMapping("/x")
	public ResponseEntity<?> x(@RequestBody ProjectCreateModel model) {
		Date publishDate = Date.valueOf(model.getPublishDate());
		Date dueDate = Utils.addDate(publishDate, model.getDeliverDays());

		Project project = Project.builder()
				.name(model.getName())
				.publisherId(model.getPublisherId())
				.description(model.getDescription())
				.wage(model.getWage())
				.imageURL(model.getImageURL())
				.deliverDays(model.getDeliverDays())
				.publishDate(publishDate)
				.dueDate(dueDate)
				.build();

		Project created = service.save(project);
		int id = created.getId();

		model.getCategories().forEach(
				category -> pcdServie.save(
						ProjectCategoryDetail.builder()
								.projectId(id)
								.categoryId(category)
								.build()));

		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody ProjectCreateModel model) {

		Date publishDate = Date.valueOf(model.getPublishDate());
		Date dueDate = Utils.addDate(publishDate, model.getDeliverDays());

		Project project = Project.builder()
				.name(model.getName())
				.publisherId(model.getPublisherId())
				.description(model.getDescription())
				.wage(model.getWage())
				.imageURL(model.getImageURL())
				.deliverDays(model.getDeliverDays())
				.publishDate(publishDate)
				.dueDate(dueDate)
				.build();

		Project created = service.save(project);
		int id = created.getId();

		// save skills
		model.getSkills().forEach(
				skill -> psrService.save(
						ProjectSkillRequire.builder()
								.projectId(id)
								.skillId(skill.getId())
								.level(skill.getLevel())
								.build()));

		// save categories
		model.getCategories().forEach(
				category -> pcdServie.save(
						ProjectCategoryDetail.builder()
								.projectId(id)
								.categoryId(category)
								.build()));

		// save deliver output

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(SubPath.ID)
				.buildAndExpand(id)
				.toUri();

		return ResponseEntity
				.created(location)
				.body(created);
	}

}
