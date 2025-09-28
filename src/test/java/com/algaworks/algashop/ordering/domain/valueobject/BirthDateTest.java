package com.algaworks.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class BirthDateTest {


    @Test
    void shouldCalculateAge() {
        BirthDate birthDate = new BirthDate(LocalDate.of(1994, 11, 10));

        int age = birthDate.age();

        Assertions.assertThat(age).isEqualTo(30);
    }
}