package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.exception.EntityFoundException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.model.ApiResponse;
import com.findhub.finhubbackend.model.model.MemberModel;
import com.findhub.finhubbackend.model.update.AccountUpdateModel;
import com.findhub.finhubbackend.model.update.MemberSkillUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.MemberUpdateModel;
import com.findhub.finhubbackend.model.update.MemberUpdateSkillModel;
import com.findhub.finhubbackend.model.update.StatusUpdateModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.memberSkill.MemberSkillService;
import com.findhub.finhubbackend.service.skill.SkillService;
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
    private AccountService accountService;
    
    @Autowired
    private MemberService memberService;

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
    	Account account = accountService.get(id);
    	if (account != null) {
    		MemberModel m = service.getModel(account.getEmail());
    		return new ResponseEntity<>(m, HttpStatus.OK);    		
    	}else return new ResponseEntity<>("Member is not exist", HttpStatus.FAILED_DEPENDENCY);  
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Member> members = service.getAll();

        if (members.isEmpty())
            throw new EntityNotFoundException("No member found");

        List<MemberModel> mrm = new ArrayList<>();
        members.forEach(member -> {
        	MemberModel model = service.getModel(member.getId());
        	if (model != null) mrm.add(model);
        });

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mrm);
    }
    
    @Override
    public ResponseEntity<?> updateStatus(@RequestBody StatusUpdateModel u) {
    	int id = u.getId();
    	int status = u.getStatus();
    	
    	String entityName = "Member";
    	
    	Member entity = service.get(id);
    	return (service.updateStatus(id, status))
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                        ApiResponse
                            .builder()
                                // .status(HttpStatus.OK)
                                .message(
                                    "Updated"
                                        + entityName + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                                        + entityName + "[status=" + status + "] successfully"
                                )
                            .build()
                    )
                : ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                        ApiResponse
                            .builder()
                                // .status(HttpStatus.BAD_REQUEST)
                                .message("Failed to update " + entityName + "[id=" + id + "]")
                            .build()
                    );
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
    
    @PutMapping()
    public ResponseEntity<String> update(@RequestBody MemberUpdateModel m) {
//    	if (service.existByEmail(a.getEmail()))
//            return new ResponseEntity<>("Account email [" + a.getEmail() + "] already existed", HttpStatus.FOUND);
    	Member member = memberService.findByEmail(m.getEmail()).get();
    	m.setId(member.getId());
    	if(service.update(m)) {
        	return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
        	return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
    
    @PutMapping("/description")
    public ResponseEntity<String> updateDescription(@RequestBody MemberUpdateDescriptionModel m) {
    	Member member = memberService.findByEmail(m.getEmail()).get();
    	m.setId(member.getId());
    	if(service.updateDescription(m)) {
        	return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
        	return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
    
    @PutMapping("/skill")
    public ResponseEntity<String> updateSkill(@RequestBody MemberUpdateSkillModel m) {
    	Member member = memberService.findByEmail(m.getEmail()).get();
    	m.setId(member.getId());
    	if(service.updateSkill(m, member)) {
        	return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
        	return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
}
