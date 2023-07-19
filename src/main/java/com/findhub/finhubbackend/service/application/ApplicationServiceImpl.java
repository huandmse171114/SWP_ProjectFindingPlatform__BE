package com.findhub.finhubbackend.service.application;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.repository.ApplicationRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class ApplicationServiceImpl extends ServiceImpl<Application, ApplicationRepository, ApplicationStatus>
		implements ApplicationService {

	@Override
	public List<Application> findAllByProjectId(int id) {
		return repo.findAllByProjectId(id);
	}

	@Override
	public List<Application> findAllByDateAfter(Date date) {
		return repo.findAllByCreateDateAfter(date);
	}

	@Override
	public List<Application> findAllByDateBefore(Date date) {
		return repo.findAllByCreateDateBefore(date);
	}

	@Override
	public List<Application> findAllByProjectIdStartingWith(int projectId) {
		return repo.findAllByProjectIdStartingWith(projectId);
	}

	@Override
	public List<Application> findAllByTeamId(int teamId) {
		return repo.findAllByTeamId(teamId);
	}

	@Override
	public List<Application> findAllByTeamIdStartingWith(int teamId) {
		return repo.findAllByTeamIdStartingWith(teamId);
	}

	@Override
	public List<Application> findAllByDate(Date date) {
		return repo.findAllByCreateDate(date);
	}

	@Override
	public List<Application> findAllByDateBetween(Date fromDate, Date toDate) {
		if (fromDate.compareTo(toDate) > 0) {
			Date tempDate;

			tempDate = fromDate;
			fromDate = toDate;
			toDate = tempDate;
		}
		return repo.findAllByCreateDateBetween(fromDate, toDate);
	}

	@Override
	public boolean existsByTeamIdAndProjectId(int teamId, int projectId) {
		return repo.existsByTeamIdAndProjectId(teamId, projectId);
	}

	@Override
	public boolean existsByTeamId(String teamId) {
		return repo.existsByTeamId(teamId);
	}

	@Override
	public List<Application> findAllByProjectIdAndStatus(int id, int value) {
		return repo.findAllByProjectIdAndStatus(id, value);
	}
}
