package com.findhub.finhubbackend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.model.AcceptRejectModel;
import com.findhub.finhubbackend.model.model.InviteModel;
import com.findhub.finhubbackend.model.model.JoinModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.service.teamMember.TeamMemberService;
import com.findhub.finhubbackend.service.teamRequest.TeamRequestService;
import com.findhub.finhubbackend.util.Config.SubPath;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/team-request")
public class TeamRequestController
        extends ApiController<TeamRequest, TeamRequestService, TeamRequestStatus> {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberService memberService;

    @Override
    public ResponseEntity<?> get(int id) {
        return super.get(id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    // @PostMapping("/join")
    // public ResponseEntity<?> sendJoin(@RequestBody JoinModel model) {

    //     int sId = model.getSenderId();
    //     int rId = model.getReceiverId();
    //     int tId = model.getTeamId();

    //     Team t = teamService.get(tId);
    //     if (t == null)
    //         throw new EntityNotFoundException(Team.class, tId);

    //     // send
    //     Account am = accountService.get(sId);
    //     // receive
    //     Account al = accountService.get(rId);

    //     if (am == null)
    //         throw new EntityNotFoundException(Account.class, sId);
    //     if (al == null)
    //         throw new EntityNotFoundException(Account.class, rId);

    //     Member m = memberService.findByEmail(am.getEmail()).get();
    //     Member l = memberService.findByEmail(al.getEmail()).get();

    //     if (m == null)
    //         throw new EntityNotFoundException(Member.class);
    //     if (l == null)
    //         throw new EntityNotFoundException(Member.class);

    //     TeamRequest tr = service.save(
    //             TeamRequest
    //                     .builder()
    //                     .senderId(m.getId())
    //                     .receiverId(l.getId())
    //                     .teamId(model.getTeamId())
    //                     .message(model.getMessage())
    //                     // .status(TeamRequestStatus.REQUESTING.getValue())
    //                     .build());

    //     int id = tr.getId();

    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    //             .path(SubPath.ID)
    //             .buildAndExpand(id)
    //             .toUri();

    //     return ResponseEntity
    //             .created(location)
    //             // .body(service.getModel(id));
    //             .body(service.get(id));

    // }

    // @PostMapping("/invite")
    // public ResponseEntity<?> sendInvite(@RequestBody InviteModel model) {
    //     int sId = model.getSenderId();
    //     int rId = model.getReceiverId();
    //     int tId = model.getTeamId();

    //     Team t = teamService.get(tId);
    //     if (t == null)
    //         throw new EntityNotFoundException(Team.class, tId);

    //     // send
    //     Account al = accountService.get(sId);
    //     // receive
    //     Account am = accountService.get(rId);

    //     if (am == null)
    //         throw new EntityNotFoundException(Account.class, sId);
    //     if (al == null)
    //         throw new EntityNotFoundException(Account.class, rId);

    //     Member m = memberService.findByEmail(am.getEmail()).get();
    //     Member l = memberService.findByEmail(al.getEmail()).get();

    //     if (m == null)
    //         throw new EntityNotFoundException(Member.class);
    //     if (l == null)
    //         throw new EntityNotFoundException(Member.class);

    //     TeamRequest tr = service.save(
    //             TeamRequest
    //                     .builder()
    //                     .senderId(m.getId())
    //                     .receiverId(l.getId())
    //                     .teamId(model.getTeamId())
    //                     .message(model.getMessage())
    //                     // .status(TeamRequestStatus.INVITING.getValue())
    //                     .build());

    //     int id = tr.getId();

    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    //             .path(SubPath.ID)
    //             .buildAndExpand(id)
    //             .toUri();

    //     return ResponseEntity
    //             .created(location)
    //             // .body(service.getModel(id));
    //             .body(service.get(id));

    // }

    // @PostMapping("/accept-join")
    // public ResponseEntity<?> acceptJoin(@RequestBody AcceptRejectModel model) {
    //     int rqId = model.getRequestId();

    //     TeamRequest tr = service.get(rqId);
    //     // Team t = teamService.get(tId);
    //     if (tr == null)
    //         throw new EntityNotFoundException(TeamRequest.class, rqId);

    //     int tId = tr.getTeamId();
    //     Team t = teamService.get(tId);
    //     if (t == null)
    //         throw new EntityNotFoundException(Team.class, tId);

    //     int sId = tr.getSenderId();
    //     int rId = tr.getReceiverId();

    //     Member m = memberService.get(sId);
    //     Member l = memberService.get(rId);

    //     if (m == null)
    //         throw new EntityNotFoundException(Member.class);
    //     if (l == null)
    //         throw new EntityNotFoundException(Member.class);

    //     // tr.setStatus(TeamRequestStatus.APPROVED_REQUEST.getValue());
    //     teamMemberService.save(
    //             TeamMember
    //                     .builder()
    //                     .member(m)
    //                     .team(t)
    //                     .build());
    //     service.update(rqId, tr);

    //     return null;
    // }

    // @PostMapping("/reject-join")
    // public ResponseEntity<?> rejectJoin(@RequestBody AcceptRejectModel model) {
    //     int rqId = model.getRequestId();

    //     TeamRequest tr = service.get(rqId);
    //     // Team t = teamService.get(tId);
    //     if (tr == null)
    //         throw new EntityNotFoundException(TeamRequest.class, rqId);

    //     int tId = tr.getTeamId();
    //     Team t = teamService.get(tId);
    //     if (t == null)
    //         throw new EntityNotFoundException(Team.class, tId);

    //     int sId = tr.getSenderId();
    //     int rId = tr.getReceiverId();

    //     Member m = memberService.get(sId);
    //     Member l = memberService.get(rId);

    //     if (m == null)
    //         throw new EntityNotFoundException(Member.class);
    //     if (l == null)
    //         throw new EntityNotFoundException(Member.class);

    //     // tr.setStatus(TeamRequestStatus.REJECTED_REQUEST.getValue());
    //     // teamMemberService.save(
    //     // TeamMember
    //     // .builder()
    //     // .member(m)
    //     // .team(t)
    //     // .build());
    //     service.update(rqId, tr);

    //     return null;
    // }

    // @PostMapping("/accept-invite")
    // public ResponseEntity<?> acceptInvite(@RequestBody AcceptRejectModel model) {
    //     int rqId = model.getRequestId();

    //     TeamRequest tr = service.get(rqId);
    //     // Team t = teamService.get(tId);
    //     if (tr == null)
    //         throw new EntityNotFoundException(TeamRequest.class, rqId);

    //     int tId = tr.getTeamId();
    //     Team t = teamService.get(tId);
    //     if (t == null)
    //         throw new EntityNotFoundException(Team.class, tId);

    //     int sId = tr.getSenderId();
    //     int rId = tr.getReceiverId();

    //     Member m = memberService.get(sId);
    //     Member l = memberService.get(rId);

    //     if (m == null)
    //         throw new EntityNotFoundException(Member.class);
    //     if (l == null)
    //         throw new EntityNotFoundException(Member.class);

    //     // tr.setStatus(TeamRequestStatus.ACCEPTED_INVITATION.getValue());
    //     teamMemberService.save(
    //             TeamMember.builder()
    //                     .member(m)
    //                     .team(t)
    //                     .build());
    //     service.update(rqId, tr);

    //     return null;
    // }

    // @PostMapping("/reject-invite")
    // public ResponseEntity<?> rejectInvite(@RequestBody AcceptRejectModel model) {
    //     int rqId = model.getRequestId();

    //     TeamRequest tr = service.get(rqId);
    //     // Team t = teamService.get(tId);
    //     if (tr == null)
    //         throw new EntityNotFoundException(TeamRequest.class, rqId);

    //     int tId = tr.getTeamId();
    //     Team t = teamService.get(tId);
    //     if (t == null)
    //         throw new EntityNotFoundException(Team.class, tId);

    //     int sId = tr.getSenderId();
    //     int rId = tr.getReceiverId();

    //     Member m = memberService.get(sId);
    //     Member l = memberService.get(rId);

    //     if (m == null)
    //         throw new EntityNotFoundException(Member.class);
    //     if (l == null)
    //         throw new EntityNotFoundException(Member.class);

    //     // tr.setStatus(TeamRequestStatus.REJECTED_INVITATION.getValue());
    //     // teamMemberService.save(
    //     // TeamMember
    //     // .builder()
    //     // .member(m)
    //     // .team(t)
    //     // .build());
    //     service.update(rqId, tr);

    //     return null;
    // }
}
