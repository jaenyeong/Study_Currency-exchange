package com.jaenyeong.javacurrencyexchange.api.config;

import com.jaenyeong.javacurrencyexchange.api.domain.Currency;
import org.springframework.core.convert.converter.Converter;

public class CurrencyConverter implements Converter<String, Currency> {

    @Override
    public Currency convert(String source) {
        return Currency.valueOf(source.toUpperCase());
    }
}
