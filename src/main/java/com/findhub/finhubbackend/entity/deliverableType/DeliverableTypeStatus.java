package com.findhub.finhubbackend.entity.deliverableType;

public enum DeliverableTypeStatus {
    INACTIVE(0),
    ACTIVE(1),
    ;

    private final int value;

    private DeliverableTypeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
