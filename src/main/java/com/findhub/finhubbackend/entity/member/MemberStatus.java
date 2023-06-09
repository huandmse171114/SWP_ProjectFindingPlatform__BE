package com.findhub.finhubbackend.entity.member;

public enum MemberStatus {
	AVAILABLE(1),
	ONPROJECT(0);

	private final int value;

	private MemberStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
