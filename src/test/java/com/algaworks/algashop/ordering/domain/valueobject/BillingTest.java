package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BillingTest {

    @Test
    void shouldValidateNullValues() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(Billing.builder()::build);

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        Billing.builder().fullName(new FullName("test", "test")).build()
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        Billing.builder()
                                .fullName(new FullName("test", "test"))
                                .document(new Document("000-23-2314"))
                                .build()
                );

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        Billing.builder()
                                .fullName(new FullName("test", "test"))
                                .document(new Document("000-23-2314"))
                                .phone(new Phone("230-437-2134"))
                                .build()
                );
    }
}