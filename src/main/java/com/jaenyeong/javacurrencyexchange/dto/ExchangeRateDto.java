package com.jaenyeong.javacurrencyexchange.dto;

import java.util.Map;

public class ExchangeRateDto {

    private boolean success;
    private String source;
    private Map<String, Double> quotes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, Double> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, Double> quotes) {
        this.quotes = quotes;
    }

    public double findExchangeRateBy(final String currency) {
        return quotes.getOrDefault(currency, (double) 0);
    }
}
