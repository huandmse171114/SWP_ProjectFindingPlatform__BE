package com.findhub.finhubbackend.service.project;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.CategoryDTO;
import com.findhub.finhubbackend.dto.ProjectDTO;
import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategoryStatus;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverableStatus;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkillStatus;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.entity.teamMember.TeamMemberRole;
import com.findhub.finhubbackend.entity.teamMember.TeamMemberStatus;
import com.findhub.finhubbackend.entity.teamProject.TeamProject;
import com.findhub.finhubbackend.entity.teamProject.TeamProjectStatus;
import com.findhub.finhubbackend.model.model.ProjectDeliverableResponseModel;
import com.findhub.finhubbackend.model.model.SkillModel;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.ApplicationResponseModel;
import com.findhub.finhubbackend.model.response.ProjectCategoryResponseModel;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.model.response.ProjectSkillResponseModel;
import com.findhub.finhubbackend.model.response.TeamApplicationResponseModel;
import com.findhub.finhubbackend.model.response.TeamInProjectResponseModel;
import com.findhub.finhubbackend.model.response.TeamMemberResponseModel;
import com.findhub.finhubbackend.repository.ProjectRepository;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.service.publisher.PublisherService;
import com.findhub.finhubbackend.service.service.ServiceImpl;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.service.teamMember.TeamMemberService;
import com.findhub.finhubbackend.service.teamProject.TeamProjectService;
import com.findhub.finhubbackend.util.Utils;

@Service
public class ProjectServiceImpl
		extends ServiceImpl<Project, ProjectRepository, ProjectStatus>
		implements ProjectService {

	@Autowired
	private SkillService skillService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	@Autowired
	private TeamProjectService teamProjectService;

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

	/**
	 * bản cũ, chưa xài @manyToMany
	 */
	@Override
	public ProjectResponseModel getById(int id) {
		Project project = get(id);

		if (project == null) return null;

		List<SkillModel> skills = new ArrayList<>();
		skillService.getNameAndLevelByProjectId(id)
			.forEach(skill -> skills.add(
					SkillModel
						.builder()
							.name(skill.getName())
							.level(skill.getLevel())
						.build()
				)
			);

		String status = ProjectStatus.nameOf(project.getStatus());
		Date dueDate = Utils.addDate(project.getPublishDate(), project.getDeliverDays());

		List<CategoryDTO> categoriesObj = categoryService.getNameByProjectId(id);
		List<String> categories = new ArrayList<>();
		categoriesObj.forEach(each -> categories.add(each.getName()));

		return ProjectResponseModel
				.builder()
					.id(id)
					.name(project.getName())
					.publishDate(project.getPublishDate().toString())
					.deliverDays(project.getDeliverDays())
					.publisher(
						publisherService.get(
							project.getPublisherId()
						)
					)
					.wage(project.getWage())
					.dueDate(dueDate.toString())
					.status(status)
					// .skills(skills)
					// .categories(categories)
					.description(project.getDescription())
				.build();
	}

	public List<ProjectResponseModel> getAllByIdContaining(int id) {
		List<ProjectResponseModel> result = new ArrayList<>();

		findAllByIdContaining(id)
			.forEach(each -> result.add(
					getById(each.getId())
				)
			);

		return result;
	}

	public List<ProjectResponseModel> getAllByNameContaining(String name) {
		List<ProjectResponseModel> result = new ArrayList<>();

		findAllByNameContaining(name)
			.forEach(each -> result.add(
					getById(each.getId())
				)
			);

		return result;
	}

	public List<ProjectResponseModel> getAllByNameContainingOrIdLike(String input) {
		List<ProjectResponseModel> result = new ArrayList<>();

		if (Utils.isNum(input)) {
			int id = Integer.parseInt(input);
			getAllByNameContainingOrIdLike(id, input)
				.forEach(each -> result.add(
						getById(each.getId())
					)
				);

		} else
			getAllByNameContaining(input);

		return result;
	}

	@Override
	public ProjectResponseModel getModel(int id) {
		Project p = get(id);

		if (p == null) return null;

		List<ProjectSkillResponseModel> skills = new ArrayList<>();
		(p.getSkills()).forEach(
			skill -> skills.add(
				ProjectSkillResponseModel
					.builder()
						.id(skill.getSkill().getId())
						.projectSkillId(skill.getId())
						.name(skill.getSkill().getName())
						.level(skill.getLevel())
						.status(
							ProjectSkillStatus.nameOf(
								skill.getStatus()
							)
						)
					.build()
			)
		);

		List<ProjectCategoryResponseModel> categories = new ArrayList<>();
		List<String> categoriesStr = new ArrayList<>();
		(p.getCategories()).forEach(
			category -> {
				String name = category.getCategory().getName();
				categories.add(
					ProjectCategoryResponseModel
						.builder()
							.id(category.getCategory().getId())
							.projectCategoryId(category.getId())
							.name(name)
							.status(
								ProjectCategoryStatus.nameOf(
									category.getStatus()
								)
							)
						.build());
				categoriesStr.add(name);
			}
		);

		List<ProjectDeliverableResponseModel> deliverables = new ArrayList<>();
		(p.getDeliverables()).forEach(
			deliverable -> {
				deliverables.add(
					ProjectDeliverableResponseModel
						.builder()
							.id(deliverable.getDeliverableType().getId())
							.projectDeliverableId(deliverable.getId())
							.name(deliverable.getDeliverableType().getName())
							.value(deliverable.getValue())
							.description(deliverable.getDescription())
							.status(
								ProjectDeliverableStatus.nameOf(
									deliverable.getStatus()
								)
							)
						.build()
				);
			}
		);

		return ProjectResponseModel
				.builder()
					.id(p.getId())
					.name(p.getName())
					.publisher(
						publisherService.get(
							p.getPublisherId()
						)
					)
					.description(p.getDescription())
					.wage(p.getWage())
					.imageURL(p.getImageURL())
					.deliverDays(p.getDeliverDays())
					.publishDate(p.getPublishDate().toString())
					.dueDate(p.getDueDate().toString())
					.status(
						ProjectStatus.nameOf(
							p.getStatus()
						)
					)
					.skills(skills)
					.categories(categories)
					.deliverables(deliverables)
				.build();

	}

	@Override
	public List<Project> findALlByPublisherId(int id) {
		return repo.findALlByPublisherId(id);
	}

	@Override
	public List<Project> getAllByPublisherId(int id) {
		return repo.findALlByPublisherId(id);
	}

	@Override
	public ProjectResponseModel getPublisherModel(int id) {
		Project p = get(id);

		if (p == null) return null;

		List<ProjectSkillResponseModel> skills = new ArrayList<>();
		(p.getSkills()).forEach(
			skill -> skills.add(
				ProjectSkillResponseModel
					.builder()
						.id(skill.getSkill().getId())
						.projectSkillId(skill.getId())
						.name(skill.getSkill().getName())
						.level(skill.getLevel())
						.status(
							ProjectSkillStatus.nameOf(
								skill.getStatus()
							)
						)
					.build()
			)
		);

		List<ProjectCategoryResponseModel> categories = new ArrayList<>();
		List<String> categoriesStr = new ArrayList<>();
		(p.getCategories()).forEach(
			category -> {
				String name = category.getCategory().getName();
				categories.add(
					ProjectCategoryResponseModel
						.builder()
							.id(category.getCategory().getId())
							.projectCategoryId(category.getId())
							.name(name)
							.status(
								ProjectCategoryStatus.nameOf(
									category.getStatus()
								)
							)
						.build());
				categoriesStr.add(name);
			}
		);

		List<ProjectDeliverableResponseModel> deliverables = new ArrayList<>();
		(p.getDeliverables()).forEach(
			deliverable -> {
				deliverables.add(
					ProjectDeliverableResponseModel
						.builder()
							.id(deliverable.getDeliverableType().getId())
							.projectDeliverableId(deliverable.getId())
							.name(deliverable.getDeliverableType().getName())
							.value(deliverable.getValue())
							.description(deliverable.getDescription())
							.status(
								ProjectDeliverableStatus.nameOf(
									deliverable.getStatus()
								)
							)
						.build()
				);
			}
		);
		
		List<ApplicationResponseModel> applicationModels = new ArrayList<>();
		List<Application> applications = applicationService.findAllByProjectIdAndStatus(p.getId(), ApplicationStatus.PENDING.getValue());

		applications.forEach(application -> {
			Team team = application.getTeam();
			TeamMember leaderMember = teamMemberService.findAllByRole(TeamMemberRole.LEADER.getValue()).get(0);
			Member leader = leaderMember.getMember();
			applicationModels.add(ApplicationResponseModel.builder()
					.id(application.getId())
					.createDate(application.getCreateDate().toString())
					.message(application.getMessage())
					.team(TeamApplicationResponseModel.builder()
							.id(team.getId())
							.leaderName(leader.getName())
							.name(team.getName())
							.role(TeamMemberRole.LEADER.toString())
							.build())
					.build());
		});
		
		List<TeamInProjectResponseModel> teamProjectModel = new ArrayList<>();
		
		if (p.getStatus() == ProjectStatus.ONGOING.getValue()) {
			TeamProject teamProject = teamProjectService.findAllByProjectIdAndStatus(p.getId(), TeamProjectStatus.ONGOING.getValue()).get(0);
			Team team = teamProject.getTeam();
			List<TeamMember> members = team.getMembers();
			
			List<TeamMemberResponseModel> memberModels = new ArrayList<>();
			
			members.forEach(teamMember -> {
				Member member = teamMember.getMember();
				memberModels.add(TeamMemberResponseModel.builder()
						.id(member.getId())
						.name(member.getName())
						.email(member.getEmail())
						.role(StatusModel.builder()
								.id(teamMember.getRole())
								.name(TeamMemberRole.nameOf(teamMember.getRole()))
								.build())
						.status(StatusModel.builder()
								.id(teamMember.getStatus())
								.name(TeamMemberStatus.nameOf(teamMember.getStatus()))
								.build())
						.build());
			});
			
			teamProjectModel.add(TeamInProjectResponseModel.builder()
					.id(team.getId())
					.name(team.getName())
					.members(memberModels)
					.build());
		}
		
		return ProjectResponseModel
				.builder()
					.id(p.getId())
					.name(p.getName())
					.publisher(
						publisherService.get(
							p.getPublisherId()
						)
					)
					.description(p.getDescription())
					.wage(p.getWage())
					.imageURL(p.getImageURL())
					.deliverDays(p.getDeliverDays())
					.publishDate(p.getPublishDate().toString())
					.dueDate(p.getDueDate().toString())
					.status(
						ProjectStatus.nameOf(
							p.getStatus()
						)
					)
					.skills(skills)
					.categories(categories)
					.deliverables(deliverables)
					.applications(applicationModels)
					.team(teamProjectModel.size() > 0 ? teamProjectModel.get(0) : null)
				.build();
	}
}
