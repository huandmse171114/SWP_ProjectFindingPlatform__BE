package com.findhub.finhubbackend.controller;

import java.io.Console;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberRole;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.entity.teamMember.TeamMemberRole;
import com.findhub.finhubbackend.entity.teamMember.TeamMemberStatus;
import com.findhub.finhubbackend.entity.teamProject.TeamProject;
import com.findhub.finhubbackend.entity.teamProject.TeamProjectStatus;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestType;
import com.findhub.finhubbackend.exception.EntityFoundException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.ApplicationCreateModel;
import com.findhub.finhubbackend.model.create.TeamCreateModel;
import com.findhub.finhubbackend.model.model.ApiResponse;
import com.findhub.finhubbackend.model.model.MemberTeamModel;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.model.TeamModel;
import com.findhub.finhubbackend.model.response.MemberRequestResponseModel;
import com.findhub.finhubbackend.model.response.TeamMemberResponseModel;
import com.findhub.finhubbackend.model.response.TeamReqRequestResponseModel;
import com.findhub.finhubbackend.model.response.TeamResponseModel;
import com.findhub.finhubbackend.model.response.ViewerRequestResponseModel;
import com.findhub.finhubbackend.model.update.TeamMemberUpdateModel;
import com.findhub.finhubbackend.model.update.TeamUpdateModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.project.ProjectService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.service.teamMember.TeamMemberService;
import com.findhub.finhubbackend.service.teamProject.TeamProjectService;
import com.findhub.finhubbackend.service.teamRequest.TeamRequestService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.EntityPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;



@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.TEAM)
public class TeamController
        extends ApiController<Team, TeamService, TeamStatus> {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private TeamMemberService teamMemberService;
    
    @Autowired
    private TeamRequestService teamRequestService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private TeamProjectService teamProjectService;

    // @Autowired
    // private TeamPro;

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
    
    @GetMapping("/leader/{id}")
    public ResponseEntity<?> getAllTeamsByLeaderId(@PathVariable int id) {
    	//get all team member record of this member as a leader role
    	List<TeamMember> teamMembers = teamMemberService.findAllByMemberIdAndRole(id, TeamMemberRole.LEADER.getValue());
    	List<Team> teams = new ArrayList<>();
    	teamMembers.forEach(teamMember -> {
    		teams.add(teamMember.getTeam());
    	});
    	List<TeamResponseModel> teamResponse = new ArrayList<>();
    	teams.forEach(team -> {
    		List<TeamMemberResponseModel> memberResponseModels = new ArrayList<>();
    		team.getMembers().forEach(member -> {
    			memberResponseModels.add(TeamMemberResponseModel.builder()
    					.id(member.getMember().getId())
    					.role(StatusModel.builder()
    							.id(member.getRole())
    							.name(TeamMemberRole.nameOf(member.getRole()))
    							.build())
    					.status(StatusModel.builder()
    							.id(member.getStatus())
    							.name(TeamMemberStatus.nameOf(member.getStatus()))
    							.build())
    					.name(member.getMember().getName())
    					.email(member.getMember().getEmail())
    					.build());
    		});
    		
    		//Get all pending status request
    		List<TeamReqRequestResponseModel> requestModels = new ArrayList<>();
    		List<TeamRequest> requests = teamRequestService.findAllByReceiverIdAndTypeAndStatus(team.getId()
    				, TeamRequestType.REQUEST.getValue(), TeamRequestStatus.PENDING.getValue());
    		System.out.println("No requests: " + requests.size());
    		requests.forEach(request -> {
    			Member member = memberService.get(request.getSenderId());
    			System.out.println("Member send request id: " + member.getEmail());
    			requestModels.add(TeamReqRequestResponseModel.builder()
    					.id(request.getId())
    					.message(request.getMessage())
    					.createDate(request.getCreateDate().toString())
    					.member(MemberRequestResponseModel.builder()
    							.id(member.getId())
    							.name(member.getName())
    							.email(member.getEmail())
    							.build())
    					.build());
    		});
    		
    		List<TeamProject> teamProjects = teamProjectService.findAllByTeamIdAndStatus(team.getId(), TeamProjectStatus.ONGOING.getValue());
    		
    		
    		System.out.println("No model requests: " + requestModels.size());
    		teamResponse.add(TeamResponseModel.builder()
    							.id(team.getId())
    							.status(StatusModel.builder()
    									.id(team.getStatus())
    									.name(TeamStatus.nameOf(team.getStatus()))
    									.build())
    							.members(memberResponseModels)
    							.name(team.getName())
    							.projects(null)
    							.requests(requestModels)
    							.project(!teamProjects.isEmpty() ? teamProjects.get(0).getProject() : null)
    							.build());
    	});
    	
//    	System.out.println(teams);
//    	System.out.println(teamMembers);
    	return ResponseEntity.status(HttpStatus.OK).body(teamResponse);
    }
    
    @GetMapping("/member/{id}")
    public ResponseEntity<?> getAllTeamsByMemberId(@PathVariable int id) {
    	//get all team member record of this member as a leader role
    	List<TeamMember> teamMembers = teamMemberService.findAllByMemberIdAndRole(id, TeamMemberRole.MEMBER.getValue());
    	List<Team> teams = new ArrayList<>();
    	teamMembers.forEach(teamMember -> {
    		teams.add(teamMember.getTeam());
    	});
    	List<TeamResponseModel> teamResponse = new ArrayList<>();
    	teams.forEach(team -> {
    		List<TeamMemberResponseModel> memberResponseModels = new ArrayList<>();
    		team.getMembers().forEach(member -> {
    			System.out.println(member.getMember().getId());
    			memberResponseModels.add(TeamMemberResponseModel.builder()
    					.id(member.getMember().getId())
    					.role(StatusModel.builder()
    							.id(member.getRole())
    							.name(TeamMemberRole.nameOf(member.getRole()))
    							.build())
    					.status(StatusModel.builder()
    							.id(member.getStatus())
    							.name(TeamMemberStatus.nameOf(member.getStatus()))
    							.build())
    					.name(member.getMember().getName())
    					.email(member.getMember().getEmail())
    					.build());
    		});
    		
    		teamResponse.add(TeamResponseModel.builder()
    							.id(team.getId())
    							.status(StatusModel.builder()
    									.id(team.getStatus())
    									.name(TeamStatus.nameOf(team.getStatus()))
    									.build())
    							.members(memberResponseModels)
    							.name(team.getName())
    							.build());
    	});
    	
//    	System.out.println(teams);
//    	System.out.println(teamMembers);
    	return ResponseEntity.status(HttpStatus.OK).body(teamResponse);
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
    
    @GetMapping("/viewer/all/{id}")
    public ResponseEntity<?> getAllByViewerId(@PathVariable int id) {
    	Account account = accountService.get(id);
        List<Team> teams = service.getAll();
        Member member = memberService.findByEmail(account.getEmail()).get();

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
        
        result.forEach(team -> {
        	List<TeamRequest> teamRequest = teamRequestService.findAllBySenderIdAndType(member.getId(), TeamRequestType.REQUEST.getValue());
        	System.out.println(teamRequest);
        	if (teamRequest != null) {
        		team.setRequest(ViewerRequestResponseModel.builder()
        				.id(!teamRequest.isEmpty() ? teamRequest.get(0).getId() : null)
        				.message(teamRequest.get(0).getMessage())
        				.createDate(teamRequest.get(0).getCreateDate().toString())
        				.status(StatusModel.builder()
        						.id(teamRequest.get(0).getStatus())
        						.name(TeamRequestStatus.nameOf(teamRequest.get(0).getStatus()))
        						.build())
        				.build());
        	}else {
        		team.setRequest(null);        		
        	}
        });

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
                .forEach(member -> {                	
                	//Add team leader to TeamMember table
                	if (member.getRole() == TeamMemberRole.LEADER.getValue()) {
                		teamMemberService.save(
                				TeamMember
                				.builder()
                				.team(team)
                				.member(
                						memberService.get(
                								member.getId()
                								)
                						)
                				.role(member.getRole())
                				.build());                		
                	}
                	
                	//Send join invitation
                	if (member.getRole() != TeamMemberRole.LEADER.getValue()) {
                		teamRequestService.save(
                				TeamRequest.builder()
                				.senderId(team.getId())
                				.receiverId(member.getId())
                				.status(TeamRequestStatus.PENDING.getValue())
                				.type(TeamRequestType.INVITATION.getValue())
                				.message("Hi, " + member.getName() + ". We are looking for a talented and motivated member to join our team "
                						+ "and help us achieve our goals. If you are interested in working with us, "
                						+ "please let us know. We would love to have you on board!")
                				.build()
                				);                		
                	}
                }
                );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(SubPath.ID)
				.buildAndExpand(id)
				.toUri();

		return ResponseEntity
				.created(location)
				// .body(service.getModel(id));
				.body("Create team successfully");
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

    public ResponseEntity<?> applyProject(ApplicationCreateModel model){
        int teamId = model.getTeamId();
        int projectId = model.getProjectId();

        Team t = service.get(teamId);
        if(t == null) throw new EntityNotFoundException(entityName, teamId);

        Project p = projectService.get(projectId);
        if(p == null) throw new EntityNotFoundException(Project.class, projectId);

        //save to application

        return null;
    }

    public ResponseEntity<?> unApplyProject(ApplicationCreateModel model){
        int teamId = model.getTeamId();
        int projectId = model.getProjectId();

        Team t = service.get(teamId);
        if(t == null) throw new EntityNotFoundException(entityName, teamId);

        Project p = projectService.get(projectId);
        if(p == null) throw new EntityNotFoundException(Project.class, projectId);

        //save to application

        return null;
    }

}