package com.findhub.finhubbackend.entity.projectSkill;

import java.util.HashMap;
import java.util.Map;

import com.findhub.finhubbackend.util.Utils;

public enum ProjectSkillStatus {
    INACTIVE(0),
    ACTIVE(1),
    DELETED(2),
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

    private ProjectSkillStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
