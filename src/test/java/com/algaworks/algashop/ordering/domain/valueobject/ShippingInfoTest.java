package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ShippingInfoTest {

    @Test
    void shouldValidateNullValues() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(ShippingInfo.builder()::build);

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        ShippingInfo.builder().fullName(new FullName("test", "test")).build()
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        ShippingInfo.builder()
                                .fullName(new FullName("test", "test"))
                                .document(new Document("000-23-2314"))
                                .build()
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        ShippingInfo.builder()
                                .fullName(new FullName("test", "test"))
                                .document(new Document("000-23-2314"))
                                .phone(new Phone("230-437-2134"))
                                .build()
                );
    }
}