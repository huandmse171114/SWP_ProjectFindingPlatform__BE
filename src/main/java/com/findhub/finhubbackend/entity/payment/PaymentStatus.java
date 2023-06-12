package com.findhub.finhubbackend.entity.payment;

public enum PaymentStatus {
    PENDING(0),
    SUCCEEDED(1),
    FAILED(2),
    ;

    protected final int value;

    private PaymentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
