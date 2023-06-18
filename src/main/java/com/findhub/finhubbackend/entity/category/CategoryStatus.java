package com.findhub.finhubbackend.entity.category;

public enum CategoryStatus {
    INACTIVE(0),
    ACTIVE(1),
    ;

    private final int value;

    private CategoryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
