package com.findhub.finhubbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.model.update.StatusUpdateModel;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.ACCOUNT)
public class AccountController
        extends ApiController<Account, AccountService, AccountStatus> {

    @Override
    public ResponseEntity<?> get(int id) {
        return super.get(id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @PutMapping("/role")
    public ResponseEntity<?> setRole(
            @RequestBody StatusUpdateModel u) {
        int id = u.getId();
        int role = u.getStatus();
        Account a = service.get(id);
        // if (!AccountRole.isExisted(role))
        // return ResponseEntity.badRequest()
        // .body(ApiResponse.builder()
        // // .status(HttpStatus.BAD_REQUEST)
        // .message("role not found")
        // .build());
        a.setRole(role);
        service.update(id, a);
        return super.getAll();
    }
}
