package com.findhub.finhubbackend.entity.member;

import java.util.HashMap;
import java.util.Map;

public enum MemberRole {
    MEMBER(2),
    LEADER(3),
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

    MemberRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
