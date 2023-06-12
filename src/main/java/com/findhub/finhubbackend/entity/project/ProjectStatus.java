package com.findhub.finhubbackend.entity.project;

public enum ProjectStatus {
	DELETED(0),
	INACTIVE(1),
	ACTIVE(2),
	APPROVE(3),
	ONGOING(4),
	FINISHED(5);

	private final int value;

	private ProjectStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
