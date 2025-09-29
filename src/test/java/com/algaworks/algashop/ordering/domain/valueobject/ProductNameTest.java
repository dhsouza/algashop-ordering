package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductNameTest {

    @Test
    void shouldValidateNullValues() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        new ProductName(null)
                );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new ProductName("")
                );
    }

    @Test
    void shouldValidateValidValues() {
        ProductName test = new ProductName("test");
        Assertions.assertThat(test.value()).isEqualTo("test");
    }
}