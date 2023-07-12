package com.findhub.finhubbackend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.update.RoleUpdateModel;
import com.findhub.finhubbackend.model.update.StatusUpdateModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.publisher.PublisherService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Utils;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.ACCOUNT)
public class AccountController
        extends ApiController<Account, AccountService, AccountStatus> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PublisherService publisherService;

    @Override
    public ResponseEntity<?> get(int id) {
        return super.get(id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @PutMapping("/role")
    public ResponseEntity<?> updateRole(@RequestBody RoleUpdateModel u) {
        int id = u.getAccountId();
        int newRole = u.getRoleId();

        Account a = service.get(id);
        if(a == null) throw new EntityNotFoundException(entityName, id);

        int oldRole = a.getRole();

        if (!AccountRole.isExisted(newRole))
        throw new EntityNotFoundException("Role", newRole);

        if(oldRole == newRole) return response("nothing to update", HttpStatus.OK);

        if(oldRole == AccountRole.ADMIN.getValue()){

        } else if(oldRole == AccountRole.MEMBER.getValue()){
            Member m = memberService.get(id);
            if(m == null) throw new EntityNotFoundException("Member", id);
            memberService.updateStatus(m, Status.DELETED);
        } else if(oldRole == AccountRole.PUBLISHER.getValue()){
            Publisher p = publisherService.get(id);
            if(p == null) throw new EntityNotFoundException("Publisher", id);
            publisherService.updateStatus(p, Status.DELETED);
        }

        String roleName;
        MyEntity e;
        int newId;
        if(newRole == AccountRole.ADMIN.getValue()){
            roleName = Utils.capitalize(
                AccountRole.ADMIN.toString()
            );
            newId = -1;
        } else if(newRole == AccountRole.MEMBER.getValue()){
            roleName = Utils.capitalize(
                AccountRole.MEMBER.toString()
            );

            e = memberService.save(
                Member
                    .builder()
                        .email(a.getEmail())
                    .build()
            );
            newId = e.getId();
        } else {
            roleName = Utils.capitalize(
                AccountRole.PUBLISHER.toString()
            );

            e = publisherService.save(
                Publisher
                    .builder()
                        .email(a.getEmail())
                    .build()
            );
            newId = e.getId();
        }


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(SubPath.ID)
				.buildAndExpand(id)
				.toUri();

        a.setRole(newRole);
        service.update(id, a);

		return ResponseEntity
				.created(location)
				// .body(service.getModel(id));
				.body("Account[" + id + "] set to " + roleName + "[" + newId + "]");


    }
}
