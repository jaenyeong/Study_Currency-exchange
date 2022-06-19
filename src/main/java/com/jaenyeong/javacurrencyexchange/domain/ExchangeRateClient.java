package com.jaenyeong.javacurrencyexchange.domain;

import com.jaenyeong.javacurrencyexchange.dto.ExchangeRateDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.time.Duration.ofSeconds;

public class ExchangeRateClient {

    private final String currencyLayerRequestUrl;

    public ExchangeRateClient(final String currencyLayerUrl, final String apiKey) {
        this.currencyLayerRequestUrl = currencyLayerUrl + "?apikey=" + apiKey;
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
