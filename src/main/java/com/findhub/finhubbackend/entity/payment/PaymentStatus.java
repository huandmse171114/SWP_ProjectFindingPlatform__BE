package com.findhub.finhubbackend.entity.payment;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {
    SUCCEEDED(0),
    PENDING(1),
    FAILED(2),
    ;

    protected final int value;
    private static final Map<Integer, String> status = new HashMap<>();

    static {
        // only java 10+
        for (var ps : values())
            status.put(ps.getValue(), ps.name());

    }

    public static String nameOf(int val) {
        return status.get(val);
    }

    private PaymentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
