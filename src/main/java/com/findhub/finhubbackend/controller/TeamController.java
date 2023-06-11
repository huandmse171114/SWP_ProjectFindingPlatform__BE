package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.TEAM)
public class TeamController extends MyController<Team, TeamService, TeamStatus> {

    @PostMapping(ApiPath.ENABLE)
    public boolean enableEntity(@RequestBody int id) {
        return service.changeStatus(id, TeamStatus.ACTIVE);
    }

    @PostMapping(ApiPath.DISABLE)
    public boolean disableEntity(@RequestBody int id) {
        return service.changeStatus(id, TeamStatus.INACTIVE);
    }
}