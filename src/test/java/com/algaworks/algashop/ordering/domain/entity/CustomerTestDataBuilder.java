package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class CustomerTestDataBuilder {

    private CustomerTestDataBuilder() {
    }

    public static Customer.BrandNewCustomerBuild brandNewCustomer() {
        return Customer.brandNew()
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                .email(new Email("john.doe@gmail.com"))
                .phone(new Phone("478-256-2604"))
                .document(new Document("255-08-0578"))
                .promotionNotificationsAllowed(true)
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("New York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build());
    }

    public static Customer.ExistingCustomerBuild existingCustomer() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                .email(new Email("john.doe@gmail.com"))
                .phone(new Phone("478-256-2604"))
                .document(new Document("255-08-0578"))
                .promotionNotificationsAllowed(true)
                .archived(false)
                .registeredAt(OffsetDateTime.now())
                .archivedAt(null)
                .loyaltyPoints(new LoyaltyPoints(10))
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("New York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build());
    }

    public static Customer.ExistingCustomerBuild existingAnonymizedCustomer() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("Anonymous", "Anonymous"))
                .birthDate(null)
                .email(new Email("anonymous@anonymous.com"))
                .phone(new Phone("000-000-0000"))
                .document(new Document("000-00-0000"))
                .promotionNotificationsAllowed(false)
                .archived(true)
                .registeredAt(OffsetDateTime.now())
                .archivedAt(OffsetDateTime.now())
                .loyaltyPoints(new LoyaltyPoints(10))
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("New York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build());
    }
}
