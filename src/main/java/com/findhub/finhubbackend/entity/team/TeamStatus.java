package com.findhub.finhubbackend.entity.team;

public enum TeamStatus {
    ACTIVE(1),
    INACTIVE(0);

    private int value;

    private TeamStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
