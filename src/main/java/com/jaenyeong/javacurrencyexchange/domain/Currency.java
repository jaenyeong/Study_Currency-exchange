package com.jaenyeong.javacurrencyexchange.domain;

public enum Currency {
    KRW("한국"),
    JPY("일본"),
    PHP("필리핀");

    private final String country;

    Currency(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
