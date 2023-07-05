package com.findhub.finhubbackend.entity.member;

import java.util.HashMap;
import java.util.Map;

public enum MemberStatus {
	NOT_AVAILABLE(0),
	AVAILABLE(1),
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

	private MemberStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
