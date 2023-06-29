package com.findhub.finhubbackend.entity.member;

import java.util.HashMap;
import java.util.Map;

import com.findhub.finhubbackend.util.Utils;

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
        return Utils.capitalize(status.get(val));
    }

    public static boolean isExisted(int val) {
        return status.get(val) != null;
    }

    public static boolean isExisted(String val) {
        return status.containsValue(val.toUpperCase());
    }

    MemberRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
