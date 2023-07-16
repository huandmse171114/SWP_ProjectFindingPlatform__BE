package com.findhub.finhubbackend.service.teamRequest;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.repository.TeamRequestRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class TeamRequestServiceImpl
        extends ServiceImpl<TeamRequest, TeamRequestRepository, TeamRequestStatus>
        implements TeamRequestService{

}
