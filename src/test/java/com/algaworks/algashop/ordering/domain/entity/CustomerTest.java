package com.algaworks.algashop.ordering.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

class CustomerTest {

    @Test
    void CustomerTest() {
        Customer customer = new Customer(
                UUID.randomUUID(),
                "Jhon Doe",
                LocalDate.of(1980, 10, 11),
                "jhon@gmail.com",
                "424-424-2346",
                "255-05-0349",
                true,
                OffsetDateTime.now()
        );

        customer.addLoyaltyPoints(10);
    }
}
