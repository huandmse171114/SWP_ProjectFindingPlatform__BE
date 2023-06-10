package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.util.Config.*;

@RestController
@CrossOrigin
@RequestMapping(path = Path.MAJOR)
public class MajorController extends MyController<Major, MajorService, MajorStatus> {

	@PostMapping(Path.ENABLE)
	public boolean enableEntity(@RequestBody int id) {
		return service.changeStatus(id, MajorStatus.ACTIVE);
	}

	@PostMapping(Path.DISABLE)
	public boolean disableEntity(@RequestBody int id) {
		return service.changeStatus(id, MajorStatus.INACTIVE);
	}

	public boolean changeStatusEntity(@RequestBody int id, @RequestBody int status) {
		return service.changeStatus(id, status);
	}
}
