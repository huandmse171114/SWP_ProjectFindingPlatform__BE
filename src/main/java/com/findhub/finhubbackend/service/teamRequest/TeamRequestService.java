package com.findhub.finhubbackend.service.teamRequest;

import java.util.List;

import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface TeamRequestService
        extends Service<TeamRequest, TeamRequestStatus>{

	List<TeamRequest> findAllByReceiverId(int id);

	List<TeamRequest> findAllByReceiverIdAndStatus(int id, int value);

	List<TeamRequest> findAllByIdAndType(int id, int value);

	List<TeamRequest> findAllByReceiverIdAndType(int id, int value);

	List<TeamRequest> findAllByReceiverIdAndTypeAndStatus(int id, int value, int value2);

	List<TeamRequest> findAllBySenderIdAndType(int id, int value);

}
