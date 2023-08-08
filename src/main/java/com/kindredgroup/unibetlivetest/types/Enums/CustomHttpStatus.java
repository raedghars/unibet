package com.kindredgroup.unibetlivetest.types.Enums;

import lombok.Getter;

public enum CustomHttpStatus {
    INSUFFISANT_BALANCE(600, "Balance insuffisante"),
    ODD_CHANGED(601, "Changement de cote"),
    SELECTION_CLOSED(602, "Selection fermée");

    @Getter
    private final int value;
    private final String reason;

    CustomHttpStatus(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int value() {
        return value;
    }

    public String getReason() {
        return reason;
    }
}