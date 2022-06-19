package com.jaenyeong.javacurrencyexchange.dto;

public class RemittanceResponse {

    private final String exchangeRate;

    public RemittanceResponse(final String remittance) {
        this.exchangeRate = remittance;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }
}
