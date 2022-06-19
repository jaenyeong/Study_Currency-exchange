package com.jaenyeong.javacurrencyexchange.domain;

import com.jaenyeong.javacurrencyexchange.dto.ExchangeRateDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("환율 정보를 가져오는 요청 API 테스트")
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.yaml")
@SpringBootTest
class ExchangeRateClientTest {

    @Value("${currency-layer.url}")
    private String currencyLayerUrl;
    @Value("${currency-layer.api-key}")
    private String apiKey;
    @Value("${currency-layer.invalid-key}")
    private String invalidApiKey;

    @DisplayName("유효한 `api-key`와 `url` 프로퍼티를 사용해 환율 가져오기 성공")
    @Disabled
    @Test
    void get_request_currency_layer_is_success_when_valid_apiKey() {
        // Arrange
        ExchangeRateClient client = new ExchangeRateClient(currencyLayerUrl, apiKey);

        // Act
        final ExchangeRateDto responseDto = client.getCurrencyLayer();

        // Assert
        assertThat(responseDto.isSuccess()).isTrue();
    }

    @DisplayName("유효하지 않은 `api-key`와 `url` 프로퍼티를 사용해 환율 가져오면 `401` 에러 발생")
    @Disabled
    @Test
    void get_request_currency_layer_is_fail_when_invalid_apiKey() {
        // Arrange
        ExchangeRateClient client = new ExchangeRateClient(currencyLayerUrl, invalidApiKey);

        // Act
        // Assert
        assertThatThrownBy(client::getCurrencyLayer)
            .isInstanceOf(HttpClientErrorException.class)
            .hasMessageContaining("401 Unauthorized");
    }

    @DisplayName("`RestTemplate`을 사용하여 `apikey`를 가지고 환율 데이터 조회 시 성공")
    @Test
    @Disabled
    void get_request_currency_layer_by_restTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(ofSeconds(5L))
            .setReadTimeout(ofSeconds(5L))
            .setBufferRequestBody(false)
            .build();

        final ExchangeRateDto body = restTemplate.getForEntity(
            "https://api.apilayer.com/currency_data/live?apikey=F8maHd9Z1ZxrKIyx1h9Glr558T8XXECU",
            ExchangeRateDto.class).getBody();

        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
    }
}
