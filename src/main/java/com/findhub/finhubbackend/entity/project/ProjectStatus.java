package com.findhub.finhubbackend.entity.project;

import java.util.HashMap;
import java.util.Map;

public enum ProjectStatus {
	DELETED(0),
	INACTIVE(1),
	ACTIVE(2),
	APPROVE(3),
	ONGOING(4),
	FINISHED(5);

	private final int value;
	private static final Map<Integer, String> NAMES = new HashMap<>();

	static {
		for (ProjectStatus ps : values())
			NAMES.put(ps.getValue(), ps.name());

	}

	private ProjectStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static String nameOf(int val) {
		return NAMES.get(val);
	}
}
