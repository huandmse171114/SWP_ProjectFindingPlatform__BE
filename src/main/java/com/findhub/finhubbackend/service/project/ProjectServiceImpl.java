package com.findhub.finhubbackend.service.project;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.repository.ProjectRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class ProjectServiceImpl extends ServiceImpl<Project, ProjectRepository, ProjectStatus>
		implements ProjectService {

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
	public List<Project> findAllByTitleContaining(String title) {
		return repo.findAllByTitleContaining(title);
	}

	@Override
	public List<Project> findAllByTypeContaining(String type) {
		return repo.findAllByTypeContaining(type);
	}

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
	public Optional<Project> findByTitle(String title) {
		return repo.findByTitle(title);
	}

	@Override
	public Optional<Project> findByType(String type) {
		return repo.findByType(type);
	}

}
