package com.findhub.finhubbackend.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.findhub.finhubbackend.entity.application.Application;

public class ApplicationApprovedEvent extends ApplicationEvent{
	
	@Autowired
	private Application application;
	
	public ApplicationApprovedEvent(Application application) {
		super(application);
		this.application = application;
	}
}
