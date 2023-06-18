package com.findhub.finhubbackend.entity.account;

public enum AccountStatus {
	INACTIVE(0),
	ACTIVE(1),
	;

	private final int value;

	private AccountStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
