package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.service.application.ApplicationService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/applications")
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
}
