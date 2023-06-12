package com.findhub.finhubbackend.entity.entity;

public enum MyEntityStatus {
    DELETED(0),
    INACTIVE(1),
    ACTIVE(2),
    ;

    protected final int value;

    private MyEntityStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
