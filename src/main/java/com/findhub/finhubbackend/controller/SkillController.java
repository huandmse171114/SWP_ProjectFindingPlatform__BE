package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.SKILL)
public class SkillController extends ApiController<Skill, SkillService, SkillStatus> {

    @Override
	public ResponseEntity<?> getAll() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(null);
		// .body(service.getAll());
	}

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody String name) {
        if (service.existsByName(name))
            return new ResponseEntity<>("Skill[name=\'" + name + "\'] already existed", HttpStatus.FOUND);

        Skill skill = Skill
            .builder()
                .name(name)
            .build();

        service.save(skill);
        return new ResponseEntity<>("Added new Skill[name=\'" + name + "\'] successfully", HttpStatus.OK);
    }
}
