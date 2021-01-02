package org.blockchain.extra.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VoucherType {
    PAYMENT_VOUCHER("payment voucher"),
    OPENING_BALANCE_VOUCHER("opening balance voucher");

    private final String label;

    VoucherType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
