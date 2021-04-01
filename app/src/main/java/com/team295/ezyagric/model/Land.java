package com.team295.ezyagric.model;

import androidx.annotation.NonNull;

public class Land {
    String shape;
    String amount;

    public Land(String shape, String amount) {
        this.shape = shape;
        this.amount = amount;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public @NonNull String toString() {
        return "Land{" +
                "shape='" + shape + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
