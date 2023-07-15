package com.findhub.finhubbackend.entity.member;

import java.util.HashMap;
import java.util.Map;

public enum MemberStatus {
	GENERATED(0),
	INFORMED(1),
	VERIFIED(2),
	INACTIVATED(3),
	DELETED(4)
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

	private MemberStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
