package com.findhub.finhubbackend.entity.account;

import java.util.HashMap;
import java.util.Map;

public enum AccountStatus {
	ACTIVE(0),
	INACTIVE(1),
	DELETED(2),
	;
  
	private final int value;
	private static final Map<Integer, String> status = new HashMap<>();

	static {
		// only java 10+
		for (var ps : values())
			status.put(ps.getValue(), ps.name());

	}

	public static String nameOf(int val) {
		return status.get(val);
	}

	private AccountStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
