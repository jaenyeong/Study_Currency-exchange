package com.jaenyeong.javacurrencyexchange.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.DecimalFormat;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("기준이 되는 돈을 담는 객체 테스트")
class RemittanceTest {

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

    @DisplayName("0보다 같거나 크거나 10_000보다 작은 범위의 값이 주어지면 `Remittance` 인스턴스 생성 성공")
    @ParameterizedTest(name = "[{index}] -> value: {0}")
    @ValueSource(doubles = {0.017019, 0.1, 1.12345, 1_000, 5_321.982172, 9_999, 9_999.241377})
    void create_instance_is_success_given_values_in_valid_range(final double value) {
        // Arrange
        final String expectedValue = decimalFormat.format(value);

        // Act
        final Remittance source = new Remittance(value);

        // Assert
        assertThat(source.toPrintFormat()).isEqualTo(expectedValue);
    }

    @DisplayName("0보다 작거나 10_000보다 큰 범위의 값이 주어지면 `Remittance` 인스턴스 생성 실패")
    @ParameterizedTest(name = "[{index}] -> invalidValue: {0}")
    @ValueSource(doubles = {-21.91, -3.7217, -1, 10_001, 178_281_741})
    void create_instance_is_fail_given_values_in_invalid_range(final double invalidValue) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Remittance(invalidValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("송금액이 바르지 않습니다.");
    }

    @DisplayName("특정 통화에 대한 `USD` 환율이 주어지면 송금액과 계산 성공")
    @ParameterizedTest(name = "[{index}] -> baseRemittance: {0}, exchangeRate: {1}, expectedValue: {2}")
    @MethodSource("remittanceAndExchangeRateAndExpectedValue")
    void calculate_success_given_exchange_rate(
        final double baseRemittance,
        final double exchangeRate,
        final String expectedValue
    ) {
        // Arrange
        final Remittance remittance = new Remittance(baseRemittance);

        // Act
        final String result = remittance.calculate(exchangeRate);

        // Assert
        assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> remittanceAndExchangeRateAndExpectedValue() {
        return Stream.of(
            Arguments.of(6_000.721, 1_121.419945, "6,729,328.21"),
            Arguments.of(6_000.721, 110.959498, "665,836.99"),
            Arguments.of(6_000.721, 52.72027, "316,359.63"),
            Arguments.of(6_000.721, 484.684999, "2,908,459.45"),
            Arguments.of(6_000.721, 21_050.59961, "126,318,775.14")
        );
    }
}
