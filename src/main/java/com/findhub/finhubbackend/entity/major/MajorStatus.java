package com.findhub.finhubbackend.entity.major;

public enum MajorStatus {
	ACTIVE(1),
	INACTIVE(0);
	
	private int value;
	
	private MajorStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
