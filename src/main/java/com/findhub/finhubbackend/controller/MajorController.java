package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.model.create.MajorCreateModel;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MAJOR)
public class MajorController extends ApiController<Major, MajorService, MajorStatus> {

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody MajorCreateModel model) {
		String code = model.getCode();
		String name = model.getName();

		if (service.existsByCode(code))
			return new ResponseEntity<>("Major[code=\'" + code + "\'] already existed", HttpStatus.FOUND);

		if (service.existsByName(name))
			return new ResponseEntity<>("Major[name=\'" + name + "\'] already existed", HttpStatus.FOUND);

		Major major = Major.builder()
				.code(code)
				.name(name)
				.build();

		service.save(major);
		return new ResponseEntity<>("Added new Major[code=" + code + "; name=\'" + name + "\'] successfully",
				HttpStatus.OK);
	}
}
