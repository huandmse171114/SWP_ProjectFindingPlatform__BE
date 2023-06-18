package com.findhub.finhubbackend.entity.application;

public enum ApplicationStatus {
	APPROVED(0),
	PENDING(1),
	REJECTED(2),
	DELETED(99),
	;

	private final int value;

	private ApplicationStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
