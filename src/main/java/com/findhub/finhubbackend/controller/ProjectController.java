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
import org.springframework.web.bind.annotation.PutMapping;
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
import com.findhub.finhubbackend.model.update.ProjectCategoryUpdateModel;
import com.findhub.finhubbackend.model.update.ProjectDeliverableUpdateModel;
import com.findhub.finhubbackend.model.update.ProjectSkillUpdateModel;
import com.findhub.finhubbackend.model.update.ProjectUpdateModel;
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
import com.findhub.finhubbackend.util.Utils;

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
		// Project pt = service.get(id);

		if (p == null)
			throw new EntityNotFoundException(entityName, id);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(p);
				// .body(pt);
	}

	@Override
	public ResponseEntity<?> getAll() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(getResponseModels(service.getAll()));
		// .body(service.getAll());
	}

	//getByMemberId
	//getByTeamId
	//getByPublisherId

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

	@PutMapping()
	// @Override
	public ResponseEntity<?> update(@RequestBody ProjectUpdateModel model) {
		int projectId = model.getId();

		Project old = service.get(projectId);

		String name = model.getName();
		if (!Utils.isNullOrEmpty(name)) old.setName(name);

		old.setPublisherId(model.getPublisherId());

		String des = model.getDescription();
		if (!Utils.isNullOrEmpty(des)) old.setDescription(des);

		old.setWage(model.getWage());

		String img = model.getImageURL();
		if (!Utils.isNullOrEmpty(img)) old.setImageURL(img);

		old.setDeliverDays(model.getDeliverDays());

		// old.setPublishDate();

		String due = model.getDueDate();
		if (!Utils.isNullOrEmpty(due))
			old.setDueDate(
				Date.valueOf(due)
			);

		old.setStatus(model.getStatus());

		//update project
		service.update(old);

		// update skill
		model.getSkills()
			.forEach(
				skill -> psService.updateById(
					skill.getId(),
					skill.getSkillId(),
					skill.getStatus(),
					skill.getLevel(),
					projectId
				)
			);

		// update deliverable
		model.getDeliverables()
			.forEach(
				d -> pdService.updateById(
					d.getId(),
					d.getDeliverableId(),
					d.getValue(),
					d.getStatus(),
					d.getDescription(),
					projectId
				)
			);

		//update category
		model.getCategories()
			.forEach(
				category -> pcServie.updateById(
					category.getId(),
					category.getStatus(),
					category.getCategoryId(),
					projectId
				)
			);

		return new ResponseEntity<>(service.getModel(projectId), HttpStatus.OK);
	}

	@PutMapping("/skills")
	public ResponseEntity<?> updateSkill(@RequestBody ProjectSkillUpdateModel model) {

		int projectId = model.getProjectId();
		int skillId = model.getSkillId();

		Project p = service.get(projectId);

		if(p == null) throw new EntityNotFoundException(entityName, projectId);

		for(var s : p.getSkills())
			if(skillId == s.getSkill().getId()){
				s.setStatus(model.getStatus());
				s.setLevel(model.getLevel());
				psService.update(s);
				break;
			}

		// psService.updateById(
		// 	model.getId(),
		// 	skillId,
		// 	model.getStatus(),
		// 	model.getLevel(),
		// 	projectId
		// );
		return ResponseEntity.ok().body("update success");
		// return ResponseEntity.ok().body("update success");
	}

	@PutMapping("/deliverables")
	public ResponseEntity<?> updateDelvierable(@RequestBody ProjectDeliverableUpdateModel model) {

		int projectId = model.getProjectId();
		int delverableId = model.getDeliverableId();

		Project p = service.get(projectId);

		if(p == null) throw new EntityNotFoundException(entityName, projectId);

		for(var dts : p.getDeliverables())
			if(delverableId == dts.getDeliverableType().getId()){
				dts.setDeliverableType(
					deliverableTypeService.get(delverableId)
				);
				dts.setValue(model.getValue());
				dts.setDescription(model.getDescription());
				dts.setStatus(model.getStatus());
				pdService.update(dts);
				break;
			}

		pdService.updateById(
			model.getId(),
			model.getDeliverableId(),
			model.getValue(),
			model.getStatus(),
			model.getDescription(),
			model.getProjectId()
		);

		return ResponseEntity.ok().body("update success");
	}

	@PutMapping("/categories")
	public ResponseEntity<?> updateCategory(@RequestBody ProjectCategoryUpdateModel model) {

		int projectId = model.getProjectId();
		int cateId = model.getCategoryId();

		Project p = service.get(projectId);

		if(p == null) throw new EntityNotFoundException(entityName, projectId);

		pcServie.updateById(
			model.getId(),
			model.getStatus(),
			cateId,
			projectId
		);

		return ResponseEntity.ok().body("update success");
	}

}