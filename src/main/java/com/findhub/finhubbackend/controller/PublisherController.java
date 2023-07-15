package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.mail.MailRequest;
import com.findhub.finhubbackend.entity.mail.MailType;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.entity.publisher.PublisherStatus;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.teamProject.TeamProject;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.response.PublisherResponseModel;
import com.findhub.finhubbackend.model.update.MemberUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.PublisherUpdateDescriptionModel;
import com.findhub.finhubbackend.model.update.PublisherUpdateModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.application.ApplicationService;
import com.findhub.finhubbackend.service.email.EmailService;
import com.findhub.finhubbackend.service.publisher.PublisherService;
import com.findhub.finhubbackend.service.teamProject.TeamProjectService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PUBLISHER)
public class PublisherController
        extends ApiController<Publisher, PublisherService, PublisherStatus> {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private TeamProjectService tpService;

    @Autowired
    private EmailService emailService;
    
    @Autowired 
    private AccountService accountService;
    
    @Autowired
    private PublisherService publisherService;

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
    	Account account = accountService.get(id);
    	if (account != null) {
    		return ResponseEntity
    				.status(HttpStatus.OK)
    				.body(service.getResponseModelByEmail(account.getEmail()));    		
    	}else {
    		return ResponseEntity
    				.status(HttpStatus.FAILED_DEPENDENCY)
    				.body("Publisher is not exist");    
    	}
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Publisher> publishers = service.getAll();

        if (publishers.isEmpty())
            return response("No" + entityName + "found", HttpStatus.NOT_FOUND);

        List<PublisherResponseModel> prm = new ArrayList<>();
        publishers.forEach(
            each -> prm.add(
                service.getResponseModelById(
                    each.getId()
                )
            )
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(prm);
    }

    public ResponseEntity<?> acceptTeam(int applicationId){
        // get application
        Application a = applicationService.get(applicationId);

        if(a  == null)
            throw new EntityNotFoundException(Application.class, applicationId);

        Team t = a.getTeam();
        Project p = a.getProject();

        List<TeamProject> tps = tpService.findAllByProjectId(p.getId());

        boolean isExisted = false;
        for(var tp : tps){
            MailType mt = MailType.REJECTED;
            if(t.getId() == tp.getTeam().getId()){
                if(tp.getStatus() == Status.INACTIVE.getValue()){
                    tp.setStatus(Status.ACTIVE.getValue());
                    tpService.update(tp);
                }
                mt = MailType.APPROVED;
                isExisted = true;
            }

            for(var tm : tp.getTeam().getMembers()){
                MailRequest mr = MailRequest
                    .builder()
                        .subject("Response on Application of Project \" " + p.getName() + "\"")
                        .to(tm.getMember().getEmail())
                    .build();

                Map<String, Object> map = new HashMap<>();
                map.put("name", tm.getMember().getName());
                map.put("team", tp.getTeam().getName());

                emailService.sendEmail(mr, map, mt);
            }
        }

        if(!isExisted)
            tpService.save(
                TeamProject
                    .builder()
                        .team(t)
                        .project(p)
                    .build()
            );

        return response("success", HttpStatus.OK);
    }

    public ResponseEntity<?> rejectTeam(int applicationId){
        Application a = applicationService.get(applicationId);

        if(a  == null)
            throw new EntityNotFoundException(Application.class, applicationId);

        Team t = a.getTeam();
        Project p = a.getProject();

        for(var m : t.getMembers()){
            MailRequest mr = MailRequest
                .builder()
                    .subject("Response on Application of Project \" " + p.getName() + "\"")
                    .to(m.getMember().getEmail())
                .build();

            Map<String, Object> map = new HashMap<>();
            map.put("name", m.getMember().getName());
            map.put("team", t.getName());

            emailService.sendEmail(mr, map, MailType.REJECTED);
        }


        return null;
    }
    
    @PutMapping()
    public ResponseEntity<String> update(@RequestBody PublisherUpdateModel publisherModel) {
    	System.out.println(publisherModel.getEmail());
    	Publisher publisher = publisherService.findByEmail(publisherModel.getEmail()).get();
    	System.out.println(publisherModel.getEmail());
    	publisherModel.setId(publisher.getId());
    	
    	if(service.update(publisherModel)) {
        	return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
        	return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
    
    @PutMapping("/description")
    public ResponseEntity<String> updateDescription(@RequestBody PublisherUpdateDescriptionModel m) {
    	Publisher publisher = publisherService.findByEmail(m.getEmail()).get();
    	m.setId(publisher.getId());
    	if(service.updateDescription(m)) {
        	return new ResponseEntity<>("Update Information successfully", HttpStatus.OK);            	
        }else {
        	return new ResponseEntity<>("Update Information failed", HttpStatus.FAILED_DEPENDENCY);   
        }
    }
}
