package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class QuantityTest {

    @Test
    void given_invalidValue_whenTryCreateQuantity_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Quantity(-1));
    }

    @Test
    void given_validValue_whenTryCreateQuantity_shouldGenerateQuantity() {
        Quantity quantity = new Quantity(10);
    }

    @Test
    void givenInvalidValue_whenTryAddQuantity_shouldGenerateException() {
        Quantity quantity = new Quantity(10);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> quantity.add(new Quantity(-1)));

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> quantity.add(new Quantity(null)));
    }
}