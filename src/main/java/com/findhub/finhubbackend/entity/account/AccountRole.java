package com.findhub.finhubbackend.entity.account;


public enum AccountRole {
	ADMIN(0),
	PUBLISHER(1),
	MEMBER(2);
	
	private int value;
	
	private AccountRole(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
