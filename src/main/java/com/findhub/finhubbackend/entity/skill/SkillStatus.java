package com.findhub.finhubbackend.entity.skill;

public enum SkillStatus {
    ACTIVE(1),
    INACTIVE(0);

    private int value;

    private SkillStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
