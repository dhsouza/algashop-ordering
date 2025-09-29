package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MoneyTest {

    @Test
    void shouldValidateNullValues() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new Money("-1")
                        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new Money(new BigDecimal("-10"))
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        new Money((String) null)
                );
    }

    @Test
    void shouldValidateValidValues() {
        Money money = new Money("10");
        Money money1 = new Money(new BigDecimal("10"));

        Assertions.assertThat(money.value()).isEqualTo(new BigDecimal("10.00"));
        Assertions.assertThat(money1.value()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    void shouldDivide() {
        Money money = new Money("10");
        Money money1 = new Money(new BigDecimal("10"));

        Money divided = money.divide(money1);

        Assertions.assertThat(divided.value()).isEqualTo(new BigDecimal("1.00"));
    }

    @Test
    void shouldAdd() {
        Money money = new Money("10");
        Money money1 = new Money(new BigDecimal("10"));

        Money added = money.add(money1);

        Assertions.assertThat(added.value()).isEqualTo(new BigDecimal("20.00"));
    }

    @Test
    void shouldMultiply() {
        Money money = new Money("10");
        Quantity quantity = new Quantity(2);

        Money multiply = money.multiply(quantity);

        Assertions.assertThat(multiply.value()).isEqualTo(new BigDecimal("20.00"));
    }

    @Test
    void givenInvalidValue_whenTryAddMoney_shouldGenerateException() {
        Money money = new Money("10");

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                        .isThrownBy(() -> money.add(null));
    }

    @Test
    void givenInvalidValue_whenTryMultiplyMoney_shouldGenerateException() {
        Money money = new Money("10");

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                        .isThrownBy(() -> money.multiply(null));
    }

    @Test
    void givenZeroOrLessValue_whenTryMultiplyMoney_shouldGenerateException() {
        Money money = new Money("10");

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                        .isThrownBy(() -> money.multiply(new Quantity(0)));
    }

    @Test
    void givenInvalidValue_whenTryDivideMoney_shouldGenerateException() {
        Money money = new Money("10");

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                        .isThrownBy(() -> money.divide(null));
    }

}