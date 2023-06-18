package com.findhub.finhubbackend.entity.entity;

public enum Status {
    INACTIVE(0),
    ACTIVE(1),
    DELETED(99),
    ;

    protected final int value;

    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
