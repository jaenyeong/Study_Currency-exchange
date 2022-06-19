package com.jaenyeong.javacurrencyexchange.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("기준이 되는 돈을 담는 객체 테스트")
class RemittanceTest {

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

    @DisplayName("0보다 같거나 크거나 10_000보다 작은 범위의 값이 주어지면 `SourceMoney` 인스턴스 생성 성공")
    @ParameterizedTest(name = "12")
    @ValueSource(doubles = {0.1, 1, 1_000, 9_999})
    void create_SourceMoney_instance_is_success_given_values_in_valid_range(final double value) {
        // Arrange
        final String expectedValue = decimalFormat.format(value);

        // Act
        final Remittance source = new Remittance(value);

        // Assert
        assertThat(source.toPrintFormat()).isEqualTo(expectedValue);
    }

    @DisplayName("0보다 작거나 10_000보다 큰 범위의 값이 주어지면 `SourceMoney` 인스턴스 생성 실패")
    @ParameterizedTest(name = "34")
    @ValueSource(doubles = {-21.91, -3.7217, -1, 10_001, 178_281_741})
    void create_SourceMoney_instance_is_fail_given_values_in_invalid_range(final double invalidValue) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Remittance(invalidValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("송금액이 바르지 않습니다.");
    }
}
