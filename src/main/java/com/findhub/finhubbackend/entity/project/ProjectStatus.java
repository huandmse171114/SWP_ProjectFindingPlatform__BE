package com.findhub.finhubbackend.entity.project;

public enum ProjectStatus {
	APPROVE(0),
	ONGOING(1),
	FINISHED(2);
	
	private int value;
	
	private ProjectStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
