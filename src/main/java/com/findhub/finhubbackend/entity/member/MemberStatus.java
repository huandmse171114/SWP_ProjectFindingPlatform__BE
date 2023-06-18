package com.findhub.finhubbackend.entity.member;

public enum MemberStatus {
	NOT_AVAILABLE(0),
	AVAILABLE(1),
	DELETED(0),
	;

	private final int value;

	private MemberStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
