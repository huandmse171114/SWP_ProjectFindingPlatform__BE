package com.findhub.finhubbackend.entity.team;

public enum TeamStatus {
    INACTIVE(0),
	ACTIVE(1),
	;

    private final int value;

    private TeamStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
