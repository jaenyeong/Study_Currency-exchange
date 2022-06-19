package com.jaenyeong.javacurrencyexchange.service;

import com.jaenyeong.javacurrencyexchange.domain.Currency;
import com.jaenyeong.javacurrencyexchange.domain.ExchangeRateClient;
import com.jaenyeong.javacurrencyexchange.domain.Remittance;
import com.jaenyeong.javacurrencyexchange.dto.ExchangeRateDto;
import com.jaenyeong.javacurrencyexchange.dto.RemittanceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    @Value("${currency-layer.url}")
    private String currencyLayerUrl;
    @Value("${currency-layer.api-key}")
    private String apiKey;

    public RemittanceResponse getExchangeRate(
        final Currency source,
        final Currency target,
        final double givenRemittance
    ) {
        final Remittance remittance = new Remittance(givenRemittance);

        final ExchangeRateDto exchangeRateDto = new ExchangeRateClient(currencyLayerUrl, apiKey).getExchangeRate();
        final double exchangeRate = exchangeRateDto.findExchangeRateBy(source.toString() + target);

        return new RemittanceResponse(remittance.calculate(exchangeRate));
    }
}
