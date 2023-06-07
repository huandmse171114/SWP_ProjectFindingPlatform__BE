package com.findhub.finhubbackend.entity.category;

public enum CategoryStatus {
    ACTIVE(1),
    INACTIVE(0);

    private int value;

    private CategoryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
