package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ShippingTest {

    @Test
    void shouldValidateNullValues() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(Shipping.builder()::build);

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        Shipping.builder().cost(new Money("10")).build()
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        Shipping.builder()
                                .cost(new Money("10"))
                                .expectedDate(LocalDate.now().plusWeeks(1))
                                .build()
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        Shipping.builder()
                                .cost(new Money("10"))
                                .expectedDate(LocalDate.now().plusWeeks(1))
                                .recipient(Recipient.builder()
                                        .fullName(new FullName("test", "test"))
                                        .document(new Document("000-23-2314"))
                                        .phone(new Phone("230-437-2134"))
                                        .build()
                                )
                                .build()
                );
    }
}