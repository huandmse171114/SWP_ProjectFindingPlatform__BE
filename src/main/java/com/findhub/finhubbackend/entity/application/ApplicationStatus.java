package com.findhub.finhubbackend.entity.application;

public enum ApplicationStatus {
	PENDING(0),
	APPROVED(1),
	REJECTED(2);
	
	private int value;
	
	private ApplicationStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
