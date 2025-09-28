package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

class CustomerTest {

    @Test
    void CustomerTest() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1980, 10, 11),
                "jhon@gmail.com",
                "424-424-2346",
                "255-05-0349",
                true,
                OffsetDateTime.now()
        );

        System.out.println(customer.id());
        System.out.println(IdGenerator.generateTimeBasedUUID());

        customer.addLoyaltyPoints(10);
    }
}
