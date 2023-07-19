package com.findhub.finhubbackend.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequest;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestStatus;
import com.findhub.finhubbackend.entity.teamRequest.TeamRequestType;
import com.findhub.finhubbackend.exception.EntityFoundException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.model.ApiResponse;
import com.findhub.finhubbackend.model.model.InviteModel;
import com.findhub.finhubbackend.model.model.MemberModel;
import com.findhub.finhubbackend.model.update.MemberSkillUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.MemberUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateSkillModel;
import com.findhub.finhubbackend.model.update.PublisherUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.PublisherUpdateModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.memberSkill.MemberSkillService;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.service.teamMember.TeamMemberService;
import com.findhub.finhubbackend.service.teamRequest.TeamRequestService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MEMBER)
public class MemberController
        extends ApiController<Member, MemberService, MemberStatus> {

    @Autowired
    private MemberSkillService memberSkillService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TeamRequestService trService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService tmService;

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
        Account account = accountService.get(id);
        if (account != null) {
            MemberModel m = service.getModel(account.getEmail());
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Member is not exist", HttpStatus.FAILED_DEPENDENCY);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Member> members = service.getAll();

        if (members.isEmpty())
            throw new EntityNotFoundException("No member found");

        List<MemberModel> mrm = new ArrayList<>();
        members.forEach(member -> {
            MemberModel model = service.getModel(member.getId());
            if (model != null)
                mrm.add(model);
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mrm);
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody MemberUpdateModel memberModel) {
        System.out.println(memberModel.getEmail());
        Member member = service.findByEmail(memberModel.getEmail()).get();
        System.out.println(member.getEmail());
        memberModel.setId(member.getId());
        
        if(service.update(memberModel)) {
            return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
            return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
    
    @PutMapping("/description")
    public ResponseEntity<String> updateDescription(@RequestBody MemberUpdateDescriptionModel m) {
        Member member = service.findByEmail(m.getEmail()).get();
        m.setId(member.getId());
        if(service.updateDescription(m)) {
            return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
            return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
    
    @PutMapping("/skill")
    public ResponseEntity<String> updateSkill(@RequestBody MemberUpdateSkillModel m) {
        Member member = service.findByEmail(m.getEmail()).get();
        m.setId(member.getId());
        if(service.updateSkill(m, member)) {
            return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
            return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }

    public ResponseEntity<?> addSkill(@RequestBody MemberSkillUpdateModel model) {
        Account a = accountService.get(model.getAccountId());
        if (a == null)
            return new ResponseEntity<>("Member is not exist", HttpStatus.FAILED_DEPENDENCY);

        Member m = service.findByEmail(a.getEmail()).get();

        // Member m = service.get(memberId);
        if (m == null)
            throw new EntityNotFoundException("Cannot find Member");

        int memberId = m.getId();
        int skillId = model.getSkillId();

        Skill s = skillService.get(skillId);
        if (s == null)
            throw new EntityNotFoundException(Skill.class, memberId);

        if (service.isExistedInMember(skillId, memberId))
            throw new EntityFoundException("Skill[" + skillId + "] found in Member[" + memberId + "]");

        memberSkillService.save(
                MemberSkill
                        .builder()
                        .member(m)
                        .skill(s)
                        .build());

        return ResponseEntity
                .ok()
                .body(
                        ApiResponse
                                .builder()
                                .message("success")
                                .build());
    }

    public ResponseEntity<?> removeSkill(@RequestBody MemberSkillUpdateModel model) {
        Account a = accountService.get(model.getAccountId());
        if (a == null)
            return new ResponseEntity<>("Member is not exist", HttpStatus.FAILED_DEPENDENCY);

        Member m = service.findByEmail(a.getEmail()).get();

        // Member m = service.get(memberId);
        if (m == null)
            throw new EntityNotFoundException("Cannot find Member");

        int memberId = m.getId();
        int skillId = model.getSkillId();

        Skill s = skillService.get(skillId);
        if (s == null)
            throw new EntityNotFoundException("Skill", memberId);

        if (service.isExistedInMember(skillId, memberId))
            throw new EntityFoundException("Skill[" + skillId + "] found in Member[" + memberId + "]");

        for (var ms : m.getSkills())
            if (skillId == ms.getSkill().getId()) {
                memberSkillService.delete(ms);
                break;
            }

        return ResponseEntity
                .ok()
                .body(
                        ApiResponse
                                .builder()
                                .message("success")
                                .build());
    }

    @PostMapping("/accept/{teamRequestId}")
    public ResponseEntity<?> acceptInvite(@PathVariable int teamRequestId) {
        TeamRequest tr = trService.get(teamRequestId);
        if (tr == null)
            throw new EntityNotFoundException(TeamRequest.class, teamRequestId);

        if (tr.getStatus() != TeamRequestStatus.PENDING.getValue())
            return response("not pendding", HttpStatus.BAD_REQUEST);

        if (tr.getType() != TeamRequestType.INVITATION.getValue())
            return response("failed acceptInvite()", HttpStatus.FAILED_DEPENDENCY);

        int tId = tr.getSenderId();
        int mId = tr.getReceiverId();

        Team t = teamService.get(tId);
        if (t == null)
            throw new EntityNotFoundException(Team.class, teamRequestId);

        Member m = service.get(mId);
        if (m == null)
            throw new EntityNotFoundException(Member.class, teamRequestId);

        boolean isExisted = false;
        for (var mx : t.getMembers()) {
            if (mx.getMember().getId() == mId) {
                isExisted = true;
                throw new EntityFoundException(Member.class, mId);
            }
        }

        if (!isExisted) {
            tmService.save(
                    TeamMember
                            .builder()
                            .team(t)
                            .member(m)
                            .build());
            tr.setStatus(TeamRequestStatus.APPROVED.getValue());
            trService.save(tr);
        }

        return response("accepted", HttpStatus.OK);
    }

    @PostMapping("/reject/{teamRequestId}")
    public ResponseEntity<?> rejectInvite(@PathVariable int teamRequestId) {
        TeamRequest tr = trService.get(teamRequestId);
        if (tr == null)
            throw new EntityNotFoundException(TeamRequest.class, teamRequestId);

        if (tr.getStatus() != TeamRequestStatus.PENDING.getValue())
            return response("not pendding", HttpStatus.BAD_REQUEST);

        if (tr.getType() != TeamRequestType.INVITATION.getValue())
            return response("failed acceptInvite()", HttpStatus.FAILED_DEPENDENCY);

        tr.setStatus(TeamRequestStatus.REJECTED.getValue());
        trService.save(tr);

        return response("rejected", HttpStatus.OK);
    }

    // @PostMapping("/accept/{teamRequestId}")
    public ResponseEntity<?> acceptRequest(@PathVariable int teamRequestId) {
        TeamRequest tr = trService.get(teamRequestId);
        if (tr == null)
            throw new EntityNotFoundException(TeamRequest.class, teamRequestId);

        if (tr.getStatus() != TeamRequestStatus.PENDING.getValue())
            return response("not pendding", HttpStatus.BAD_REQUEST);

        if (tr.getType() != TeamRequestType.REQUEST.getValue())
            return response("failed acceptInvite()", HttpStatus.FAILED_DEPENDENCY);

        int tId = tr.getReceiverId();
        int mId = tr.getSenderId();

        Team t = teamService.get(tId);
        if (t == null)
            throw new EntityNotFoundException(Team.class, teamRequestId);

        Member m = service.get(mId);
        if (m == null)
            throw new EntityNotFoundException(Member.class, teamRequestId);

        boolean isExisted = false;
        for (var mx : t.getMembers()) {
            if (mx.getMember().getId() == mId) {
                isExisted = true;
                throw new EntityFoundException(Member.class, mId);
            }
        }

        if (!isExisted) {
            tmService.save(
                    TeamMember
                            .builder()
                            .team(t)
                            .member(m)
                            .build());
            tr.setStatus(TeamRequestStatus.APPROVED.getValue());
            trService.save(tr);
        }

        return response("accepted", HttpStatus.OK);
    }

    // @PostMapping("/reject/{teamRequestId}")
    public ResponseEntity<?> rejectRequest(@PathVariable int teamRequestId) {
        TeamRequest tr = trService.get(teamRequestId);
        if (tr == null)
            throw new EntityNotFoundException(TeamRequest.class, teamRequestId);

        if (tr.getStatus() != TeamRequestStatus.PENDING.getValue())
            return response("not pendding", HttpStatus.BAD_REQUEST);

        if (tr.getType() != TeamRequestType.REQUEST.getValue())
            return response("failed acceptInvite()", HttpStatus.FAILED_DEPENDENCY);

        tr.setStatus(TeamRequestStatus.REJECTED.getValue());
        trService.save(tr);

        return response("rejected", HttpStatus.OK);
    }

    @PostMapping("/invite/{accountId}")
    public ResponseEntity<?> invite(@RequestBody InviteModel model) {

        int sId = model.getSenderId(); // team
        int rId = model.getReceiverId(); // member

        Team t = teamService.get(sId);
        if (t == null)
            throw new EntityNotFoundException(Team.class, sId);

        Account a = accountService.get(rId);
        if (a == null)
            throw new EntityNotFoundException(Account.class, rId);

        Member m = service.findByEmail(a.getEmail()).get();
        if (m == null)
            throw new EntityNotFoundException(Account.class, rId);

        TeamRequest tr = trService.save(
                TeamRequest
                        .builder()
                        .senderId(t.getId())
                        .receiverId(m.getId())
                        .message(model.getMessage())
                        .status(TeamRequestStatus.PENDING.getValue())
                        .type(TeamRequestType.INVITATION.getValue())
                        // .status(TeamRequestStatus.INVITING.getValue())
                        .build());

        // int id = tr.getId();

        return response("sended invitation", HttpStatus.OK);
    }

    public ResponseEntity<?> request(@RequestBody InviteModel model) {
        int sId = model.getSenderId(); // member
        int rId = model.getReceiverId(); // team

        Team t = teamService.get(sId);
        if (t == null)
            throw new EntityNotFoundException(Team.class, sId);

        Account a = accountService.get(rId);
        if (a == null)
            throw new EntityNotFoundException(Account.class, rId);

        Member m = service.findByEmail(a.getEmail()).get();
        if (m == null)
            throw new EntityNotFoundException(Account.class, rId);

        TeamRequest tr = trService.save(
                TeamRequest
                        .builder()
                        .senderId(t.getId())
                        .receiverId(m.getId())
                        .message(model.getMessage())
                        .status(TeamRequestStatus.PENDING.getValue())
                        .type(TeamRequestType.REQUEST.getValue())
                        .build());

        // int id = tr.getId();

        return response("sended invitation", HttpStatus.OK);
    }
}
