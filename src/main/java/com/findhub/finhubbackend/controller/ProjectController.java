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
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.ProjectCreateModel;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.service.deliverable.DeliverableService;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.projectCategory.ProjectCategoryService;
import com.findhub.finhubbackend.service.projectDeliverable.ProjectDeliverableService;
import com.findhub.finhubbackend.service.projectSkill.ProjectSkillService;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {

	@Autowired
	private ProjectSkillService psService;

	@Autowired
	private ProjectCategoryService pcServie;

	@Autowired
	private ProjectDeliverableService pdService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DeliverableService deliverableTypeService;

	private List<ProjectResponseModel> getResponseModels(List<Project> projects) {
		if (projects.isEmpty())
			throw new EntityNotFoundException("No projects found");

		List<ProjectResponseModel> result = new ArrayList<>();

		projects.forEach(
			p -> result.add(
				service.getModel(
					p.getId()
				)
			)
		);

		return result;
	}

	@Override
	public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
		ProjectResponseModel p = service.getModel(id);

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
			.body(getResponseModels(service.getAll()));
		// .body(service.getAll());
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody ProjectCreateModel model) {

		Date dueDate = Date.valueOf(model.getDueDate());

		Project project = Project
			.builder()
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
				skill -> psService.save(
					ProjectSkill
						.builder()
							.project(created)
							.skill(
								skillService.get(
									skill.getId()
								)
							)
							.level(skill.getLevel())
						.build()
				)
			);

		// save categories
		model.getCategories()
			.forEach(
				category -> pcServie.save(
					ProjectCategory
						.builder()
							.project(created)
							.category(
								categoryService.get(category)
							)
						.build()
				)
			);

		// save deliver output
		model.getDeliverableTypes()
			.forEach(
				deliverable -> pdService.save(
					ProjectDeliverable
						.builder()
							.project(created)
							.value(deliverable.getValue())
							.description(deliverable.getDescription())
							.deliverableType(
								deliverableTypeService.get(
									deliverable.getId()
								)
							)
						.build()
				)
			);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path(SubPath.ID)
			.buildAndExpand(id)
			.toUri();

		return ResponseEntity
			.created(location)
			// .body(service.getModel(id));
			.body(service.get(id));
	}

}