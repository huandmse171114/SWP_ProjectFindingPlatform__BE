package com.findhub.finhubbackend.entity.teamRequest;

import java.util.HashMap;
import java.util.Map;

import com.findhub.finhubbackend.util.Utils;

public enum TeamRequestType {
	REQUEST(0),
    INVITATION(1),
    ;

    protected final int value;
    private static final Map<Integer, String> status = new HashMap<>();

    static {
        // only java 10+
        for (var ps : values())
            status.put(ps.getValue(), ps.name());

    }

    public static String nameOf(int val) {
        return Utils.capitalize(
            status.get(val)
        );
    }

    private TeamRequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}