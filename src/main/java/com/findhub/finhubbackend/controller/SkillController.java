package com.findhub.finhubbackend.controller;

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

    @PostMapping(ApiPath.ENABLE)
    public boolean enableEntity(@RequestBody int id) {
        return service.updateStatus(id, SkillStatus.ACTIVE);
    }

    @PostMapping(ApiPath.DISABLE)
    public boolean restoreEntity(@RequestBody int id) {
        return service.updateStatus(id, SkillStatus.INACTIVE);
    }
}
