package com.findhub.finhubbackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.exception.EntityFoundException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.model.ApiResponse;
import com.findhub.finhubbackend.model.model.MemberModel;
import com.findhub.finhubbackend.model.model.RequestJoinModel;
import com.findhub.finhubbackend.model.update.MemberSkillUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateModel;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.memberSkill.MemberSkillService;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.util.Config.ApiPath;
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
    private TeamService teamService;

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
        MemberModel m = service.getModel(id);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Member> members = service.getAll();

        if (members.isEmpty())
            throw new EntityNotFoundException("No member found");

        List<MemberModel> mrm = new ArrayList<>();
        members.forEach(
            each -> mrm.add(
                service.getModel(
                    each.getId()
                )
            )
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mrm);
    }

    public ResponseEntity<?> update(@RequestBody MemberUpdateModel model) {
        int memberId = model.getMemberId();
        Member m = service.get(memberId);
        m.setDescription(model.getDescription());
        m.setEmail(model.getEmail());
        m.setName(model.getName());
        m.setDob(
            Date.valueOf(model.getDob())
        );
        m.setPhone(model.getPhone());

        // List<MemberSkill> oldSkills = m.getSkills();
        // int count = 0;
        // for(var os : oldSkills){
        //     for(var ns : model.getSkills()){
        //         if(os.getSkill().getId() == ns.getSkillId()){

        //         }
        //     }
        // }

        service.update(m);
        return null;
    }

    public ResponseEntity<?> addSkill(@RequestBody MemberSkillUpdateModel model){
        int memberId = model.getMemberId();
        int skillId = model.getSkillId();

        Member m = service.get(memberId);
        if(m == null) throw new EntityNotFoundException(entityName, memberId);

        Skill s = skillService.get(skillId);
        if(s == null) throw new EntityNotFoundException("Skill", memberId);

        if(service.isExistedInMember(skillId, memberId))
            throw new EntityFoundException("Skill[" + skillId + "] found in Member[" + memberId +"]");

        memberSkillService.save(
            MemberSkill
                .builder()
                    .member(m)
                    .skill(s)
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

    public ResponseEntity<?> removeSkill(@RequestBody MemberSkillUpdateModel model){

        int memberId = model.getMemberId();
        int skillId = model.getSkillId();

        Member m = service.get(memberId);
        if(m == null) throw new EntityNotFoundException(entityName, memberId);

        Skill s = skillService.get(skillId);
        if(s == null) throw new EntityNotFoundException("Skill", memberId);

        if(service.isExistedInMember(skillId, memberId))
            throw new EntityFoundException("Skill[" + skillId + "] found in Member[" + memberId +"]");

        for(var ms : m.getSkills())
            if(skillId == ms.getSkill().getId()){
                memberSkillService.delete(ms);
                break;
            }

        return ResponseEntity
                    .ok()
                    .body(
                        ApiResponse
                            .builder()
                                .message("success")
                            .build()
                    );
    }

    public ResponseEntity<?> requestJoin(@RequestBody RequestJoinModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team t = teamService.get(teamId);
        if(t == null) throw new EntityNotFoundException(entityName, teamId);

        Member m = service.get(memberId);
        if(m == null) throw new EntityNotFoundException(entityName, memberId);

        for(var tm : t.getMembers())
            if(tm.getMember().getId() == memberId)
                throw new EntityFoundException(Member.class, memberId);

        

        return null;
    }

    public ResponseEntity<?> inviteJoin(@RequestBody RequestJoinModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team t = teamService.get(teamId);
        if(t == null) throw new EntityNotFoundException(entityName, teamId);

        Member m = service.get(memberId);
        if(m == null) throw new EntityNotFoundException(entityName, memberId);

        for(var tm : t.getMembers())
            if(tm.getMember().getId() == memberId)
                throw new EntityFoundException(Member.class, memberId);



        return null;
    }

    public ResponseEntity<?> acceptJoin(@RequestBody RequestJoinModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team t = teamService.get(teamId);
        if(t == null) throw new EntityNotFoundException(entityName, teamId);

        Member m = service.get(memberId);
        if(m == null) throw new EntityNotFoundException(entityName, memberId);

        for(var tm : t.getMembers())
            if(tm.getMember().getId() == memberId)
                throw new EntityFoundException(Member.class, memberId);



        return null;
    }

    public ResponseEntity<?> rejectJoin(@RequestBody RequestJoinModel model){
        int memberId = model.getMemberId();
        int teamId = model.getTeamId();

        Team t = teamService.get(teamId);
        if(t == null) throw new EntityNotFoundException(entityName, teamId);

        Member m = service.get(memberId);
        if(m == null) throw new EntityNotFoundException(entityName, memberId);

        for(var tm : t.getMembers())
            if(tm.getMember().getId() == memberId)
                throw new EntityFoundException(Member.class, memberId);



        return null;
    }
}
