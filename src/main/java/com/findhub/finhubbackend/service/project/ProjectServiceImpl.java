package com.findhub.finhubbackend.service.project;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.CategoryDTO;
import com.findhub.finhubbackend.dto.ProjectDTO;
import com.findhub.finhubbackend.dto.SkillDTO;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.repository.ProjectRepository;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.service.service.ServiceImpl;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Utils;

@Service
public class ProjectServiceImpl extends ServiceImpl<Project, ProjectRepository, ProjectStatus>
		implements ProjectService {

	@Autowired
	private SkillService skillService;

	@Autowired
	private CategoryService categoryService;

	@Override
	public List<Project> findAllByDeliverDays(int days) {
		return repo.findAllByDeliverDays(days);
	}

	@Override
	public List<Project> findAllByDeliverDaysGreaterThanEqual(int days) {
		return repo.findAllByDeliverDaysGreaterThanEqual(days);
	}

	@Override
	public List<Project> findAllByDeliverDaysLessThanEqual(int days) {
		return repo.findAllByDeliverDaysLessThanEqual(days);
	}

	@Override
	public List<Project> findAllByPublishDate(Date publishDate) {
		return repo.findAllByPublishDate(publishDate);
	}

	@Override
	public List<Project> findAllByPublishDateAfter(Date publishDate) {
		return repo.findAllByPublishDateAfter(publishDate);
	}

	@Override
	public List<Project> findAllByPublishDateBefore(Date publishDate) {
		return repo.findAllByPublishDateBefore(publishDate);
	}

	@Override
	public List<Project> findAllByNameContaining(String title) {
		return repo.findAllByNameContaining(title);
	}

	// @Override
	// public List<Project> findAllByDeliverableTypeId(int id) {
	// return repo.findAllByDeliverableTypeId(id);
	// }

	@Override
	public List<Project> findAllByWage(float wage) {
		return repo.findAllByWage(wage);
	}

	@Override
	public List<Project> findAllByWageGreaterThanEqual(float wage) {
		return repo.findAllByWageGreaterThanEqual(wage);
	}

	@Override
	public List<Project> findAllByWageLessThanEqual(float wage) {
		return repo.findAllByWageLessThanEqual(wage);
	}

	@Override
	public List<Project> findAllByWageBetween(float start, float end) {
		return repo.findAllByWageBetween(start, end);
	}

	@Override
	public Optional<Project> findByName(String title) {
		return repo.findByName(title);
	}

	@Override
	public List<ProjectDTO> getAllByNameContainingOrIdLike(int id, String name) {
		return repo.getAllByNameContainingOrIdLike(id, name);
	}

	@Override
	public ProjectResponseModel getById(int id) {
		Project project = get(id);

		if (project == null)
			return null;

		List<SkillDTO> skills = skillService.getNameAndLevelByProjectId(id);

		String status = ProjectStatus.nameOf(project.getStatus());
		Date dueDate = Utils.addDate(project.getPublishDate(), project.getDeliverDays());

		List<CategoryDTO> categoriesObj = categoryService.getNameByProjectId(id);
		List<String> categories = new ArrayList<>();
		categoriesObj.forEach(each -> categories.add(each.getName()));

		return ProjectResponseModel.builder()
				.id(id)
				.name(project.getName())
				.publishDate(Utils.formatDate(project.getPublishDate()))
				.deliverDays(project.getDeliverDays())
				.wage(project.getWage())
				.dueDate(Utils.formatDate(dueDate))
				.status(status)
				.skills(skills)
				.categories(categories)
				.description(project.getDescription())
				.build();
	}

	public List<ProjectResponseModel> getAllByIdContaining(int id) {
		List<ProjectResponseModel> result = new ArrayList<>();

		findAllByIdContaining(id)
				.forEach(each -> result.add(getById(each.getId())));

		return result;
	}

	public List<ProjectResponseModel> getAllByNameContaining(String name) {
		List<ProjectResponseModel> result = new ArrayList<>();

		findAllByNameContaining(name)
				.forEach(each -> result.add(getById(each.getId())));

		return result;
	}

	public List<ProjectResponseModel> getAllByNameContainingOrIdLike(String input) {
		List<ProjectResponseModel> result = new ArrayList<>();

		if (Utils.isNum(input)) {
			int id = Integer.parseInt(input);
			getAllByNameContainingOrIdLike(id, input)
					.forEach(each -> result.add(
							getById(
									each.getId())));

		} else
			getAllByNameContaining(input);

		return result;
	}

	// @Override
	// public Optional<Project> findByDeliverableTypeId(int id) {
	// return repo.findByDeliverableTypeId(id);
	// }

	// @Override
	// public boolean existsByTeamIdAndProjectId(int teamId, int projectId) {
	// return repo.existsByTeamIdAndId(teamId, projectId);
	// }

}
