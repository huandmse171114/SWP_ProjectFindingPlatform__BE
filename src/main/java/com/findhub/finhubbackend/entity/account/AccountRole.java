package com.findhub.finhubbackend.entity.account;

public enum AccountRole {
	ADMIN(0, "ADMIN"),
	PUBLISHER(1, "PUBLISHER"),
	MEMBER(2, "MEMBER");

	private final int value;
	private final String stringValue;

	AccountRole(int value, String stringValue) {
		this.value = value;
		this.stringValue = stringValue;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.stringValue;
	}
}
