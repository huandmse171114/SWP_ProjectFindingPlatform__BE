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
	public boolean changeStatus(int id) {
		// Optional<Application> application = applicationRepository.findById(id);
		// if (application.isPresent()) {
		// int status = application.get().getStatus();
		// status = (status == 2) ? 0 : status++;
		// application.get().setStatus(status);
		// return true;
		// }
		return false;
	}

	@Override
	public boolean delete(int id) {
		Optional<Application> application = applicationRepository.findById(id);
		return delete(application.get());
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
	public List<Application> findApplicationsByDate(Date date) {
		return applicationRepository.findAccountsByDate(date);
	}

	@Override
	public List<Application> findApplicationsByProjectId(int projectId) {
		return applicationRepository.findAccountsByProjectId(projectId);
	}

	@Override
	public List<Application> findApplicationsByStatus(int status) {
		return applicationRepository.findAccountsByStatus(status);
	}

	@Override
	public List<Application> findApplicationsByTeamId(int teamId) {
		return null;
	}

	@Override
	public Application findById(int id) {
		Optional<Application> application = applicationRepository.findById(id);
		return application.isPresent() ? application.get() : null;
	}

	@Override
	public List<Application> getAll() {
		return applicationRepository.findAll();
	}

	@Override
	public void save(Application application) {
		applicationRepository.save(application);
	}

	@Override
	public Application update(int id, Application application) {
		if (application != null) {
			Optional<Application> old = applicationRepository.findById(id);
			if (old.isPresent())
				return applicationRepository.save(application);
		}
		return null;
	}

}
