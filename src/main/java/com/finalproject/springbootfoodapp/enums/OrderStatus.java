package com.finalproject.springbootfoodapp.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    REJECTED("REJECTED"),
    PENDING("PENDING..."),
    ACCEPTED("PREPARING..."),
    PICKED_UP("DELIVERING..."),
    DELIVERED("DELIVERED");

    private final String info;

    OrderStatus(String info) {
        this.info = info;
    }
}
