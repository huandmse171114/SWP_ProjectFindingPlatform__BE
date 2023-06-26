package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.model.MemberResponseModel;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MEMBER)
public class MemberController extends ApiController<Member, MemberService, MemberStatus> {

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
        MemberResponseModel m = service.getResponseModelById(id);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Member> members = service.getAll();

        if (members.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(members);

        List<MemberResponseModel> mrm = new ArrayList<>();
        members.forEach(each -> mrm.add(service.getResponseModelById(each.getId())));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mrm);
    }
}
