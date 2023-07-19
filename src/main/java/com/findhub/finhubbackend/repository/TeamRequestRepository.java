package com.findhub.finhubbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;

public interface TeamRequestRepository
        extends Repo<TeamRequest>{

	List<TeamRequest> findAllBySenderIdAndType(int id, int value);

	List<TeamRequest> findAllByReceiverId(int id);

	List<TeamRequest> findAllByReceiverIdAndStatus(int id, int value);

	List<TeamRequest> findAllByIdAndType(int id, int value);

	List<TeamRequest> findAllByReceiverIdAndType(int id, int value);

	List<TeamRequest> findAllByReceiverIdAndTypeAndStatus(int id, int value, int value2);

}
