package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {
        UUID id = IdGenerator.generateTimeBasedUUID();
        LocalDate birthDate = LocalDate.of(1991, 7, 5);
        OffsetDateTime now = OffsetDateTime.now();

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> {
                    new Customer(
                            id,
                            "John Doe",
                            birthDate,
                            "invalid",
                            "478-256-2504",
                            "255-08-0578",
                            false,
                            now
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdatedCustomerEmail_shouldGenerateException() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@gmail.com",
                "478-256-2504",
                "255-08-0578",
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> {
                    customer.changeEmail("Email is invalid");
                });
    }
}
