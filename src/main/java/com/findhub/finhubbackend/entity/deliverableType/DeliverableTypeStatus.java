package com.findhub.finhubbackend.entity.deliverableType;

public enum DeliverableTypeStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int value;

    private DeliverableTypeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
