package com.findhub.finhubbackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;
import com.findhub.finhubbackend.model.ProjectCreateModel;
import com.findhub.finhubbackend.model.ProjectResponseModel;
import com.findhub.finhubbackend.model.SkillRepsonseModel;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.projectSkillRequire.ProjectSkillRequireService;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PROJECT)
public class ProjectController extends ApiController<Project, ProjectService, ProjectStatus> {
	@Autowired
	private ProjectSkillRequireService psrService;

	private ProjectResponseModel getProjectById(int id) {
		Project project = service.findById(id);

		if (project == null)
			return null;

		String status = ProjectStatus.nameOf(project.getStatus());
		Date dueDate = Utils.addDate(project.getPublishDate(), project.getDeliverDays());

		List<SkillRepsonseModel> skills = new ArrayList<>();
		List<ProjectSkillRequire> psrList = psrService.findAllByProjectId(id);
		for (ProjectSkillRequire psr : psrList)
			skills.add(SkillRepsonseModel.builder()
					.name(psr.getSkill().getName())
					.level(psr.getLevel())
					.build());

		List<String> categories = new ArrayList<>();
		for (var c : project.getCategorySet())
			categories.add(c.getName());

		return ProjectResponseModel.builder()
				.id(id)
				.name(project.getName())
				.publishDate(project.getPublishDate())
				.deliverDays(project.getDeliverDays())
				.wage(project.getWage())
				.dueDate(dueDate)
				.status(status)
				.skills(skills)
				.categories(categories)
				.description(project.getDescription())
				.build();

	}

	@GetMapping("/detail-page/" + ApiPath.ID)
	public ResponseEntity<ProjectResponseModel> getProjectForProjectDetailPage(@PathVariable(Var.ID) int id) {
		return new ResponseEntity<ProjectResponseModel>(getProjectById(id), HttpStatus.OK);
	}

	@GetMapping("/detail-page/all")
	public List<ProjectResponseModel> getAllProject() {
		List<Project> projects = service.getAll();

		if (projects.isEmpty())
			return null;

		List<ProjectResponseModel> prm = new ArrayList<>();
		for (var p : projects)
			prm.add(getProjectById(p.getId()));
		return prm;
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
