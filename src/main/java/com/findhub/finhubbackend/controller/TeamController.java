package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.model.MemberTeamModel;
import com.findhub.finhubbackend.model.model.TeamModel;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.TEAM)
public class TeamController extends ApiController<Team, TeamService, TeamStatus> {

    @Autowired
    private MemberService memberService;

    private TeamModel getTeam(int id) {
        Team team = service.get(id);

        if (team == null)
            throw new EntityNotFoundException(entityName, id);

        String status = Utils.capitalize(TeamStatus.nameOf(team.getStatus()));

        List<MemberTeamModel> members = new ArrayList<>();
        memberService.getAllByTeamId(id)
                .forEach(member -> 
                    members.add(
                        MemberTeamModel.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .build()
                    )
                );

        return TeamModel.builder()
                .id(id)
                .name(team.getName())
                .balance(team.getBalance())
                .status(status)
                .members(members)
                .build();
    }

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getTeam(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Team> teams = service.getAll();

        if (teams.isEmpty())
            throw new EntityNotFoundException("No team found");

        List<TeamModel> result = new ArrayList<>();
        teams.forEach(
            team -> result.add(
                getTeam(team.getId())
            )
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    // public ResponseEntity<?>
}