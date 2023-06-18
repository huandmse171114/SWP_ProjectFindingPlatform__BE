package com.findhub.finhubbackend.entity.skill;

public enum SkillStatus {
    INACTIVE(0),
	ACTIVE(1),
	;

    private final int value;

    private SkillStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
