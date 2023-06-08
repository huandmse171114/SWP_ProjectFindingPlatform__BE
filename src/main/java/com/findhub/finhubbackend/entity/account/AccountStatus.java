package com.findhub.finhubbackend.entity.account;

public enum AccountStatus {
	ACTIVE(1),
	INACTIVE(0),
	;

	private final int value;

	private AccountStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
