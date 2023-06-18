package com.findhub.finhubbackend.entity.major;

public enum MajorStatus {
	INACTIVE(0),
	ACTIVE(1),
	;

	private final int value;

	private MajorStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
