package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.SkillCreateModel;
import com.findhub.finhubbackend.model.response.ProjectResponseModel;
import com.findhub.finhubbackend.model.response.SkillResponseModel;
import com.findhub.finhubbackend.model.update.SkillUpdateModel;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.SKILL)
public class SkillController extends ApiController<Skill, SkillService, SkillStatus> {
    private List<SkillResponseModel> getResponseModels(List<Skill> skills) {
        if (skills.isEmpty())
            throw new EntityNotFoundException("No Skill found");

        List<SkillResponseModel> result = new ArrayList<>();
        skills.forEach(skill -> {
            result.add(service.getModel(skill.getId()));
        });
        return result;
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody SkillCreateModel skillModel) {
        if (service.existsByName(skillModel.getName()))
            return new ResponseEntity<>("Skill " + skillModel.getName() + " already existed", HttpStatus.FOUND);

        if (skillModel.getName().isBlank()) {
            return new ResponseEntity<>("Please fill in all fields", HttpStatus.EXPECTATION_FAILED);
        }

        Skill skill = Skill
                .builder()
                .name(skillModel.getName())
                .build();

        service.save(skill);
        return new ResponseEntity<>("Added new Skill " + skillModel.getName() + " successfully", HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody SkillUpdateModel skillModel) {
        if (service.existsByName(skillModel.getName()))
            return new ResponseEntity<>("Skill " + skillModel.getName() + skillModel.getId() + " already existed",
                    HttpStatus.FOUND);

        if (skillModel.getName().isBlank()) {
            return new ResponseEntity<>("Please fill in all fields", HttpStatus.EXPECTATION_FAILED);
        }

        if (service.update(skillModel)) {
            return new ResponseEntity<>("Update Skill successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update Skill failed", HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getResponseModels(service.getAll()));
    }
}
