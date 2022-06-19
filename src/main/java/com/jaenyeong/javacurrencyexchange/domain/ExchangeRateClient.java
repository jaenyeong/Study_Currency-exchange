package com.jaenyeong.javacurrencyexchange.domain;

import com.jaenyeong.javacurrencyexchange.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.time.Duration.ofSeconds;

@Component
public class ExchangeRateClient {

    final String currencyLayerRequestUrl;

    public ExchangeRateClient(
        @Value("${currency-layer.url}") String currencyLayerUrl,
        @Value("${currency-layer.api-key}") String currencyKey
    ) {
        this.currencyLayerRequestUrl = currencyLayerUrl + "?apikey=" + currencyKey;
    }

    private final RestTemplate restTemplate = new RestTemplateBuilder()
        .setConnectTimeout(ofSeconds(5L))
        .setReadTimeout(ofSeconds(5L))
        .setBufferRequestBody(false)
        .build();

    public ExchangeRateDto getCurrencyLayer() {
        return Objects.requireNonNull(
            restTemplate.getForEntity(
                currencyLayerRequestUrl,
                ExchangeRateDto.class
            ).getBody()
        );
    }
}
