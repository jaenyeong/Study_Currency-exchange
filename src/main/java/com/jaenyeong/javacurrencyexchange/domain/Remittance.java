package com.jaenyeong.javacurrencyexchange.domain;

import java.text.DecimalFormat;

public final class Remittance {

    private static final String ERR_MESSAGE_INVALID_MONEY = "송금액이 바르지 않습니다.";
    private final double remittance;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

    public Remittance(final double remittance) {
        assertValueIsInTheValidRange(remittance);
        this.remittance = remittance;
    }

    private void assertValueIsInTheValidRange(final double remittance) {
        if (10_000 >= remittance && remittance >= 0) {
            return;
        }

        throw new IllegalArgumentException(ERR_MESSAGE_INVALID_MONEY);
    }

    public String toPrintFormat() {
        return decimalFormat.format(remittance);
    }

    public String calculate(final double exchangeRate) {
        return decimalFormat.format(remittance * exchangeRate);
    }
}
