package com.findhub.finhubbackend.entity.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.util.Utils;

public enum ProjectStatus {
	ACTIVE(0),
	INACTIVE(1),
	ONGOING(2),
	APPROVED(3),
	FINISHED(4),
	DELETED(5),

	;

	private final int value;
	private static final Map<Integer, String> status = new HashMap<>();
	private static final List<StatusModel> model = new ArrayList<>();

	static {
		// only java 10+
		for (var ps : values()) {

			int id = ps.getValue();
			String name = Utils.capitalize(ps.name());

			status.put(id, name);
			model.add(
				StatusModel
					.builder()
						.id(id)
						.name(name)
					.build()
			);
		}

	}

	private ProjectStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static String nameOf(int val) {
		return Utils.capitalize(
			status.get(val)
		);
	}

	public static int valOf(String val) {
		val = val.toUpperCase();
		for (var s : values())
			if (s.name().equals(val))
				return s.getValue();
		return -1;
	}

	public static int nextStatus(int val) {
		int next = val++;
		return isExisted(next) ? next : 0;
	}

	public static boolean isExisted(int val) {
		return status.get(val) != null;
	}

	public static boolean isExisted(String val) {
		if (Utils.isNum(val))
			return isExisted(Integer.parseInt(val));

		val = val.toUpperCase();
		try {
			valueOf(val);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<StatusModel> getAll() {
		return model;
	}
}
