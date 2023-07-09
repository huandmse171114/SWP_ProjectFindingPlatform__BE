package com.findhub.finhubbackend.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.member.MemberRole;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.exception.EntityFoundException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.TeamCreateModel;
import com.findhub.finhubbackend.model.model.ApiResponse;
import com.findhub.finhubbackend.model.model.MemberTeamModel;
import com.findhub.finhubbackend.model.model.TeamModel;
import com.findhub.finhubbackend.model.update.TeamMemberUpdateModel;
import com.findhub.finhubbackend.model.update.TeamUpdateModel;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.service.teamMember.TeamMemberService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.EntityPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.TEAM)
public class TeamController
        extends ApiController<Team, TeamService, TeamStatus> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamMemberService teamMemberService;

    private TeamModel getResponseModel(int id) {
        Team team = service.get(id);

        if (team == null)
            throw new EntityNotFoundException(entityName, id);

        String status = Utils.capitalize(
            TeamStatus.nameOf(
                team.getStatus()
            )
        );

        List<MemberDTO> currentMembers = memberService.getAllByTeamId(id);
        List<MemberTeamModel> members = new ArrayList<>();
        if(currentMembers != null)
            currentMembers.forEach(member -> members.add(
                    MemberTeamModel
                        .builder()
                            .id(member.getId())
                            .name(member.getName())
                            .role(
                                MemberRole.nameOf(
                                    member.getRole()
                                )
                            )
                        .build()
                )
            );

        return TeamModel
            .builder()
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
            .body(getResponseModel(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Team> teams = service.getAll();

        if (teams.isEmpty())
            throw new EntityNotFoundException("No team found");

        List<TeamModel> result = new ArrayList<>();
        teams.forEach(
            team -> result.add(
                getResponseModel(
                    team.getId()
                )
            )
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TeamCreateModel model){
        Team team = service.save(
            Team
                .builder()
                    .name(model.getName())
                .build()
        );

        int id = team.getId();

        if(model.getMembers() != null)
            model.getMembers()
                .forEach(member -> teamMemberService.save(
                        TeamMember
                            .builder()
                                .team(team)
                                .member(
                                    memberService.get(
                                        member.getId()
                                    )
                                )
                                .role(member.getRole())
                            .build()
                    )
                );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(SubPath.ID)
				.buildAndExpand(id)
				.toUri();

		return ResponseEntity
				.created(location)
				// .body(service.getModel(id));
				.body(service.get(id));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody TeamUpdateModel model){
        Team team = service.get(model.getId());
        team.setName(model.getName());
        team.setStatus(model.getStatus());
        return null;
    }

    @PostMapping(EntityPath.MEMBER)
    public ResponseEntity<?> addMember(@RequestBody TeamMemberUpdateModel model){

        int teamId = model.getTeamId();
        int memberId = model.getMemberId();

        if(service.isExistedInTeam(memberId, teamId))
            throw new EntityFoundException("Found");

        teamMemberService.save(
            TeamMember
                .builder()
                    .team(service.get(teamId))
                    .member(memberService.get(memberId))
                    .role(model.getRole())
                .build()
        );

        return ResponseEntity
                .ok()
                .body(
                    ApiResponse
                        .builder()
                            .message("success")
                        .build()
                );
    }

    @DeleteMapping(EntityPath.MEMBER)
    public ResponseEntity<?> removeMember(@RequestBody TeamMemberUpdateModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team team = service.get(model.getTeamId());
        if(team == null) throw new EntityNotFoundException(entityName, teamId);

        if(!service.isExistedInTeam(model.getMemberId(), model.getTeamId()))
            throw new EntityNotFoundException("Member[" + memberId + "] not found in Team[" + teamId + "]");

        for(var tm : team.getMembers())
            if(memberId == tm.getMember().getId())
                teamMemberService.delete(tm);

        return ResponseEntity
                .ok()
                .body(
                    ApiResponse
                        .builder()
                            .message("success")
                        .build()
                );
    }

    // @PostMapping()
    public ResponseEntity<?> enableMember(@RequestBody TeamMemberUpdateModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team team = service.get(model.getTeamId());
        if(team == null) throw new EntityNotFoundException(entityName, teamId);

        if(!service.isExistedInTeam(model.getMemberId(), model.getTeamId()))
            throw new EntityNotFoundException("Member[" + memberId + "] not found in Team[" + teamId + "]");

        for(var tm : team.getMembers())
            if(memberId == tm.getMember().getId())
                teamMemberService.updateStatus(tm, Status.ACTIVE);

        return ResponseEntity
                .ok()
                .body(
                    ApiResponse
                        .builder()
                            .message("success")
                        .build()
                );
    }

    // @PostMapping()
    public ResponseEntity<?> disableMember(@RequestBody TeamMemberUpdateModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team team = service.get(model.getTeamId());
        if(team == null) throw new EntityNotFoundException(entityName, teamId);

        if(!service.isExistedInTeam(model.getMemberId(), model.getTeamId()))
            throw new EntityNotFoundException("Member[" + memberId + "] not found in Team[" + teamId + "]");

        for(var tm : team.getMembers())
            if(memberId == tm.getMember().getId())
                teamMemberService.updateStatus(tm, Status.INACTIVE);

        return ResponseEntity
                .ok()
                .body(
                    ApiResponse
                        .builder()
                            .message("success")
                        .build()
                );
    }

}