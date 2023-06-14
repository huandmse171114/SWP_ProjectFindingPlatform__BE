package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MAJOR)
public class MajorController extends ApiController<Major, MajorService, MajorStatus> {

	@PostMapping(ApiPath.ENABLE)
	public boolean enableEntity(@RequestBody int id) {
		return service.updateStatus(id, MajorStatus.ACTIVE);
	}

	@PostMapping(ApiPath.DISABLE)
	public boolean disableEntity(@RequestBody int id) {
		return service.updateStatus(id, MajorStatus.INACTIVE);
	}
}
