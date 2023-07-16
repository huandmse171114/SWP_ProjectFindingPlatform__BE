package com.findhub.finhubbackend.entity.teamMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.util.Utils;

public enum TeamMemberRole {
	LEADER(0, "LEADER"),
	MEMBER(1, "MEMBER");

	private final int value;
	private final String stringValue;

	private static final Map<Integer, String> status = new HashMap<>();
	private static final List<StatusModel> model = new ArrayList<>();

	static {
		// only java 10+
		for (var ps : values()) {

			int id = ps.getValue();
			String name = ps.name();

			status.put(id, name);
			model.add(
				StatusModel.builder()
					.id(id)
					.name(name)
					.build()
			);
		}

	}

	TeamMemberRole(int value, String stringValue) {
		this.value = value;
		this.stringValue = stringValue;
	}
	
	public static String nameOf(int val) {
		return status.get(val);
	}
	
	public static List<StatusModel> getAll() {
		return model;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.stringValue;
	}

	public static int valOf(String val) {
		val = val.toUpperCase();
		for (var s : values())
			if (s.name().equals(val))
				return s.getValue();
		return -1;
	}

	public static TeamMemberRole getRole(int val){
		for(var s : values())
			if(val == s.value) return s;
		return null;
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
}
