package com.findhub.finhubbackend.service.application;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Application> findAllByProjectIdLike(int projectId) {
		return repo.findAllByProjectIdLike(projectId);
	}

	@Override
	public List<Application> findAllByTeamId(int teamId) {
		return repo.findAllByTeamId(teamId);
	}

	@Override
	public List<Application> findAllByTeamIdLike(int teamId) {
		return repo.findAllByTeamIdLike(teamId);
	}

	@Override
	public List<Application> findAllByDate(Date date) {
		return repo.findAllByCreateAt(date);
	}

	@Override
	public List<Application> findAllByDateBetween(Date fromDate, Date toDate) {
		if (fromDate.compareTo(toDate) > 0) {
			Date tempDate;

			tempDate = fromDate;
			fromDate = toDate;
			toDate = tempDate;
		}
		return repo.findAllByCreateAtBetween(fromDate, toDate);
	}
}
