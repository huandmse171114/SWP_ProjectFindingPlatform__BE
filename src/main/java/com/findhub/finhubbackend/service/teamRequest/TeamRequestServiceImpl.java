package com.findhub.finhubbackend.service.teamRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.repository.TeamRequestRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class TeamRequestServiceImpl
        extends ServiceImpl<TeamRequest, TeamRequestRepository, TeamRequestStatus>
        implements TeamRequestService{
	
	@Autowired
	private TeamRequestRepository repo;

	@Override
	public List<TeamRequest> findAllByReceiverId(int id) {
		return repo.findAllByReceiverId(id);
	}

	@Override
	public List<TeamRequest> findAllByReceiverIdAndStatus(int id, int value) {
		return repo.findAllByReceiverIdAndStatus(id, value);
	}


	@Override
	public List<TeamRequest> findAllByIdAndType(int id, int value) {
		return repo.findAllByIdAndType(id, value);
	}


	@Override
	public List<TeamRequest> findAllByReceiverIdAndType(int id, int value) {
		return repo.findAllByReceiverIdAndType(id, value);
	}

	@Override
	public List<TeamRequest> findAllByReceiverIdAndTypeAndStatus(int id, int value, int value2) {
		return repo.findAllByReceiverIdAndTypeAndStatus(id, value, value2);
	}


	@Override
	public List<TeamRequest> findAllBySenderIdAndType(int id, int value) {
		return repo.findAllBySenderIdAndType(id, value);
	}

}
