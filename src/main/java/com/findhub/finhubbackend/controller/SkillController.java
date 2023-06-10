package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.entity.skill.SkillStatus;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.util.Config.Path;

@RestController
@CrossOrigin
@RequestMapping(path = Path.SKILL)
public class SkillController extends MyController<Skill, SkillService, SkillStatus> {

    @PostMapping(Path.ENABLE)
    public boolean enableEntity(@RequestBody int id) {
        return service.changeStatus(id, SkillStatus.ACTIVE);
    }

    @PostMapping(Path.DISABLE)
    public boolean restoreEntity(@RequestBody int id) {
        return service.changeStatus(id, SkillStatus.INACTIVE);
    }

    public boolean changeStatusEntity(@RequestBody int id, @RequestBody int status) {
        return service.changeStatus(id, status);
    }
}
