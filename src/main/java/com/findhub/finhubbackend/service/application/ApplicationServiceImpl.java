package com.findhub.finhubbackend.service.application;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Override
	public Application add(Application application) {
		return (application != null) ? applicationRepository.save(application) : null;
	}

	@Override
	public boolean changeStatus(Application application, int status) {
		if (application != null) {
			application.setStatus(status);
			return true;
		}
		return false;
	}

	@Override
	public boolean changeStatus(int id, int status) {
		Optional<Application> application = applicationRepository.findById(id);
		return changeStatus(application.isPresent() ? application.get() : null, status);
	}

	@Override
	public boolean changeStatus(Application application, ApplicationStatus status) {
		return changeStatus(application, status.getValue());
	}

	@Override
	public boolean changeStatus(int id, ApplicationStatus status) {
		return changeStatus(id, status.getValue());
	}

	@Override
	public boolean delete(Application application) {
		if (application != null) {
			applicationRepository.delete(application);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		Optional<Application> application = applicationRepository.findById(id);
		return delete(application.isPresent() ? application.get() : null);
	}

	@Override
	public Application update(Application oldApplication, Application newApplication) {
		if (newApplication != null && oldApplication != null) {
			int id = oldApplication.getId();
			newApplication.setId(id);

			return applicationRepository.save(newApplication);
		}
		return null;
	}

	@Override
	public Application update(int id, Application application) {
		Optional<Application> old = applicationRepository.findById(id);
		return update(old.isPresent() ? old.get() : null, application);
	}

	@Override
	public List<Application> getAll() {
		return applicationRepository.findAll();
	}

	@Override
	public Application findById(int id) {
		Optional<Application> application = applicationRepository.findById(id);
		return application.isPresent() ? application.get() : null;
	}

	@Override
	public List<Application> findAllByIdLike(int id) {
		return applicationRepository.findAllByIdLike(id);
	}

	@Override
	public List<Application> findAllByProjectId(int id) {
		return applicationRepository.findAllByProjectId(id);
	}

	@Override
	public List<Application> findAllByProjectIdLike(int projectId) {
		return applicationRepository.findAllByProjectIdLike(projectId);
	}

	@Override
	public List<Application> findAllByTeamId(int teamId) {
		return applicationRepository.findAllByTeamId(teamId);
	}

	@Override
	public List<Application> findAllByTeamIdLike(int teamId) {
		return applicationRepository.findAllByTeamIdLike(teamId);
	}

	@Override
	public List<Application> findAllByDate(Date date) {
		return applicationRepository.findAllByCreateAt(date);
	}

	@Override
	public List<Application> findAllByDateBetween(Date fromDate, Date toDate) {
		if (fromDate.compareTo(toDate) > 0) {
			Date tempDate;

			tempDate = fromDate;
			fromDate = toDate;
			toDate = tempDate;
		}
		return applicationRepository.findAllByCreateAtBetween(fromDate, toDate);
	}

	@Override
	public List<Application> findAllByStatus(ApplicationStatus status) {
		return findAllByStatus(status.getValue());
	}

	@Override
	public List<Application> findAllByStatus(int status) {
		return applicationRepository.findAllByStatus(status);
	}

	@Override
	public void save(Application application) {
		applicationRepository.save(application);
	}
}
