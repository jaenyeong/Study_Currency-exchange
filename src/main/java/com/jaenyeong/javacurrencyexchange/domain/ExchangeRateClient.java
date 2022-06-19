package com.jaenyeong.javacurrencyexchange.domain;

import com.jaenyeong.javacurrencyexchange.dto.ExchangeRateDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static java.time.Duration.ofSeconds;
import static java.util.Objects.requireNonNull;

public final class ExchangeRateClient {

    private final String requestUrl;

    public ExchangeRateClient(final String currencyLayerUrl, final String apiKey) {
        this.requestUrl = currencyLayerUrl + "?apikey=" + apiKey;
    }

    private final RestTemplate restTemplate = new RestTemplateBuilder()
        .setConnectTimeout(ofSeconds(5L))
        .setReadTimeout(ofSeconds(5L))
        .setBufferRequestBody(false)
        .build();

    public ExchangeRateDto getExchangeRate() {
        final ExchangeRateDto responseBody = restTemplate.getForEntity(requestUrl, ExchangeRateDto.class).getBody();

        return requireNonNull(responseBody);
    }
}
