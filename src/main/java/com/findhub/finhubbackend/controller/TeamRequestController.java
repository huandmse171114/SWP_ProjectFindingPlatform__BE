package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.service.teamRequest.TeamRequestService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/team-request")
public class TeamRequestController
        extends ApiController<TeamRequest, TeamRequestService, TeamRequestStatus>{

    @Autowired
    private TeamService teamService;

    @Override
    public ResponseEntity<?> get(int id) {
        return super.get(id);
    }

    
}