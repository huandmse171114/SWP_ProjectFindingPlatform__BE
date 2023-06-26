package com.findhub.finhubbackend.entity.project;

import java.util.HashMap;
import java.util.Map;

import com.findhub.finhubbackend.util.Utils;

public enum ProjectStatus {
	INACTIVE(0),
	ACTIVE(1),
	DELETED(99),

	APPROVED(2),
	ONGOING(3),
	FINISHED(4),
	;

	private final int value;
	private static final Map<Integer, String> status = new HashMap<>();

	static {
		// only java 10+
		for (var ps : values())
			status.put(ps.getValue(), ps.name());

	}

	private ProjectStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static String nameOf(int val) {
		return Utils.capitalize(status.get(val));
	}

	public static int nextStatus(int val) {
		int next = val++;
		return isExisted(next) ? next : 0;
	}

	public static boolean isExisted(int val) {
		return status.get(val) != null;
	}
}
