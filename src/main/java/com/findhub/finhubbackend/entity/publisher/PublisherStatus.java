package com.findhub.finhubbackend.entity.publisher;

import java.util.HashMap;
import java.util.Map;

public enum PublisherStatus {
	APPROVED(0),
	PENDING(1),
	DELETED(2),
	REJECTED(3),
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

	public static int nextStatus(int val) {
		int next = (val == 1) ? val + 2 : val++;
		return isExisted(next) ? next : 0;
	}

	public static boolean isExisted(int val) {
		return status.get(val) != null;
	}

	public int getValue() {
		return this.value;
	}

	private PublisherStatus(int value) {
		this.value = value;
	}

}
