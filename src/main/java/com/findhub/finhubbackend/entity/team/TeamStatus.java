package com.findhub.finhubbackend.entity.team;

import java.util.HashMap;
import java.util.Map;

import com.findhub.finhubbackend.util.Utils;

public enum TeamStatus {
	ACTIVE(0),
    INACTIVE(1),
    PENDING(2),
    ONGOING(3),
    DELETED(4),
    ;

    private final int value;
    private static final Map<Integer, String> status = new HashMap<>();

    static {
        // only java 10+
        for (var ps : values())
            status.put(ps.getValue(), ps.name());
    }

    public static String nameOf(int val) {
        return  Utils.capitalize(
            status.get(val)
        );
    }

    private TeamStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
