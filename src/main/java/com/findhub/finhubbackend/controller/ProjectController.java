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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.entity.projectCategoryDetail.ProjectCategoryDetail;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;
import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.ProjectCreateModel;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.projectCategoryDetail.ProjectCategoryDetailService;
import com.findhub.finhubbackend.service.projectDeliverable.ProjectDeliverableService;
import com.findhub.finhubbackend.service.projectSkillRequire.ProjectSkillRequireService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

	@Autowired
	private ProjectSkillRequireService psrService;

	@Autowired
	private ProjectCategoryDetailService pcdServie;

	@Autowired
	private ProjectDeliverableService pdService;

	private List<ProjectResponseModel> getList(List<Project> projects) {
		if (projects.isEmpty())
			throw new EntityNotFoundException("No projects found");

		List<ProjectResponseModel> result = new ArrayList<>();
		projects.forEach(
			p -> result.add(
				service.getById(
					p.getId()
				)
			)
		);

		return result;
	}

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
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(getList(service.getAll()));
	}

	@GetMapping(SubPath.ACTIVE)
	public ResponseEntity<?> getActive() {
		List<Project> projects = service.findAllByStatus(ProjectStatus.ACTIVE);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(projects);

	}

	@GetMapping(SubPath.INACTIVE)
	public ResponseEntity<?> getInActive() {
		List<Project> projects = service.findAllByStatus(ProjectStatus.INACTIVE);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(projects);

	}

	@GetMapping(SubPath.STATUS_ALL)
	public ResponseEntity<?> getStatusAll() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(ProjectStatus.getAll());
	}

	@GetMapping(SubPath.STATUS_KEYWORD)
	public ResponseEntity<?> getStatusList(@PathVariable(Var.KEYWORD) String keyword) {

		if (!ProjectStatus.isExisted(keyword))
			throw new EntityNotFoundException(keyword + " not found in Status");

		List<Project> projects;
		if (Utils.isNum(keyword)) {
			int id = Integer.parseInt(keyword);
			projects = service.findAllByStatus(id);
		} else {
			int status = ProjectStatus.valOf(keyword);
			projects = service.findAllByStatus(status);
		}

		List<ProjectResponseModel> result = getList(projects);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(result);
	}

	// @GetMapping(SubPath.STATUS_ID)
	public ResponseEntity<?> getStatusListById(@PathVariable(Var.ID) int statusId) {
		List<Project> projects = service.findAllByStatus(statusId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(projects);
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody ProjectCreateModel model) {

		Date dueDate = Date.valueOf(model.getDueDate());

		Project project = Project.builder()
				.name(model.getName())
				.publisherId(model.getPublisherId())
				.description(model.getDescription())
				.wage(model.getWage())
				.imageURL(model.getImageURL())
				.deliverDays(model.getDeliverDays())
				.dueDate(dueDate)
				.status(model.getStatus())
				.build();

		Project created = service.save(project);
		int id = created.getId();

		// save skills
		model.getSkills()
			.forEach(
				skill -> psrService.save(
					ProjectSkillRequire.builder()
						.projectId(id)
						.skillId(skill.getId())
						.level(skill.getLevel())
						.build()
				)
			);

		// save categories
		model.getCategories()
			.forEach(
				category -> pcdServie.save(
					ProjectCategoryDetail.builder()
						.projectId(id)
						.categoryId(category)
						.build()
				)
			);

		// save deliver output
		model.getOutputs()
			.forEach(
				pd -> pdService.save(
					ProjectDeliverable.builder()
						.projectId(id)
						.name(pd.getName())
						.description(pd.getDescription())
						.deliverableTypeId(pd.getDeliverableTypeId())
						.build()
				)
			);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(SubPath.ID)
				.buildAndExpand(id)
				.toUri();

		return ResponseEntity
				.created(location)
				.body(created);
	}

}