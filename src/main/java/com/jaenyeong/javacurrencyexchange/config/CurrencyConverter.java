package com.jaenyeong.javacurrencyexchange.config;

import com.jaenyeong.javacurrencyexchange.domain.Currency;
import org.springframework.core.convert.converter.Converter;

public class CurrencyConverter implements Converter<String, Currency> {

    @Override
    public Currency convert(String source) {
        return Currency.valueOf(source.toUpperCase());
    }
}
