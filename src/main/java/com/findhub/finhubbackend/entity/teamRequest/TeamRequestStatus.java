package com.findhub.finhubbackend.entity.teamRequest;

import java.util.HashMap;
import java.util.Map;

import com.findhub.finhubbackend.util.Utils;

public enum TeamRequestStatus {
	REQUESTING(0),
    INVITING(1),
    APPROVED_REQUEST(2),
    REJECTED_REQUEST(3),
    ACCEPTED_INVITATION(4),
    REJECTED_INVITATION(5),
    CANCELED(6),
    DELETED(7)
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

    private TeamRequestStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
