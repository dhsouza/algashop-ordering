package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.OrderInvalidShippingDeliveryDateException;
import com.algaworks.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import com.algaworks.algashop.ordering.domain.valueobject.*;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

class OrderTest {

    @Test
    void shouldGenerate() {
        CustomerId customerId = new CustomerId();
        Order draft = Order.draft(customerId);

        Assertions.assertThat(draft.id()).isNotNull();
        Assertions.assertThat(draft.customerId()).isEqualTo(customerId);
    }

    @Test
    void shouldAddOrderItem() {
        Order order = Order.draft(new CustomerId());
        Product product = ProductTestDataBuilder.aProductAltMousePad().build();
        ProductId productId = product.id();

        order.addItem(product, new Quantity(1));

        Assertions.assertThat(order.items()).isNotEmpty();
        Assertions.assertThat(order.items()).hasSize(1);

        Optional<OrderItem> first = order.items().stream().findFirst();

        Assertions.assertWith(first.get(),
                i -> Assertions.assertThat(i.productId()).isEqualTo(productId),
                i -> Assertions.assertThat(i.productName()).isEqualTo(new ProductName("Mouse Pad")),
                i -> Assertions.assertThat(i.price()).isEqualTo(new Money("100")),
                i -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(1))
        );
    }

    @Test
    void shouldGenerateExceptionWhenTryToChangeItemSet() {
        Order order = Order.draft(new CustomerId());

        Product product = ProductTestDataBuilder.aProductAltMousePad().build();

        order.addItem(
                product,
                new Quantity(1)
        );

        Assertions.assertThat(order.items()).isNotEmpty();
        Assertions.assertThat(order.items()).hasSize(1);

        Set<OrderItem> items = order.items();

        Assertions.assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(items::clear);
    }

    @Test
    void shouldCalculateTotals() {
        Order order = Order.draft(new CustomerId());

        order.addItem(
                ProductTestDataBuilder.aProductAltMousePad().build(),
                new Quantity(2)
        );

        order.addItem(
                ProductTestDataBuilder.aProductAltRamMemory().build(),
                new Quantity(1)
        );

        Assertions.assertThat(order.totalAmount()).isEqualTo(new Money("400"));
        Assertions.assertThat(order.totalItems()).isEqualTo(new Quantity(3));
    }

    @Test
    void givenDraftOrder_whenPlace_shouldChangeToPlaced() {
        Order order = OrderTestDataBuilder.anOrder().build();

        order.place();

        Assertions.assertThat(order.status()).isEqualTo(OrderStatus.PLACED);
    }

    @Test
    void givenPlacedOrder_whenMarkAsPaid_shouldChangeToPaid() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        order.markAsPaid();
        Assertions.assertThat(order.isPaid()).isTrue();
        Assertions.assertThat(order.paidAt()).isNotNull();
    }

    @Test
    void givenPlacedOrder_whenTryToPlace_shouldGenerateException() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(order::place);
    }

    @Test
    void givenDraftOrder_whenChangePaymentMethod_shouldAllowChange() {
        Order order = Order.draft(new CustomerId());

        order.changePaymentMethod(PaymentMethod.CREDIT_CARD);

        Assertions.assertThat(order.paymentMethod()).isEqualTo(PaymentMethod.CREDIT_CARD);
    }


    @Test
    void givenDraftOrder_whenChangeBillingInfo_shouldAllowChange() {
        Address address = Address.builder()
                .street("Bourbon Street")
                .number("1234")
                .neighborhood("North Ville")
                .complement("Apt. 114")
                .city("New York")
                .state("South California")
                .zipCode(new ZipCode("79911"))
                .build();

        BillingInfo billing = BillingInfo.builder()
                .address(address)
                .document(new Document("000-23-2314"))
                .phone(new Phone("230-437-2134"))
                .fullName(new FullName("John", "Doe"))
                .build();

        Order order = Order.draft(new CustomerId());

        order.changeBillingInfo(billing);

        Assertions.assertThat(order.billing()).isEqualTo(billing);
    }

    @Test
    void givenDraftOrder_whenChangeShippingInfo_shouldAllowChange() {
        Address address = Address.builder()
                .street("Bourbon Street")
                .number("1234")
                .neighborhood("North Ville")
                .complement("Apt. 114")
                .city("New York")
                .state("South California")
                .zipCode(new ZipCode("79911"))
                .build();

        ShippingInfo shipping = ShippingInfo.builder()
                .address(address)
                .document(new Document("000-23-2314"))
                .phone(new Phone("230-437-2134"))
                .fullName(new FullName("John", "Doe"))
                .build();

        Order order = Order.draft(new CustomerId());
        Money shippingCost = Money.ZERO;
        LocalDate deliveryDate = LocalDate.now().plusDays(1);

        order.changeShippingInfo(shipping, shippingCost, deliveryDate);

        Assertions.assertWith(order,
                o -> Assertions.assertThat(o.shipping()).isEqualTo(shipping),
                o -> Assertions.assertThat(o.shippingCost()).isEqualTo(shippingCost),
                o -> Assertions.assertThat(o.expectedDeliveryDate()).isEqualTo(deliveryDate)
        );
    }

    @Test
    void givenDraftOrderAndDeliveryDateInThePast_whenChangeShippingInfo_shouldGenerateException() {
        Address address = Address.builder()
                .street("Bourbon Street")
                .number("1234")
                .neighborhood("North Ville")
                .complement("Apt. 114")
                .city("New York")
                .state("South California")
                .zipCode(new ZipCode("79911"))
                .build();

        ShippingInfo shipping = ShippingInfo.builder()
                .address(address)
                .document(new Document("000-23-2314"))
                .phone(new Phone("230-437-2134"))
                .fullName(new FullName("John", "Doe"))
                .build();

        Order order = Order.draft(new CustomerId());
        Money shippingCost = Money.ZERO;
        LocalDate deliveryDate = LocalDate.now().minusDays(2);

        Assertions.assertThatExceptionOfType(OrderInvalidShippingDeliveryDateException.class)
                .isThrownBy(() -> order.changeShippingInfo(shipping, shippingCost, deliveryDate));
    }

    @Test
    void givenDraftOrder_whenChangeItem_shouldRecalculateTotals() {
        Order order = Order.draft(new CustomerId());
        order.addItem(
                ProductTestDataBuilder.aProductAltMousePad().build(),
                new Quantity(3));

        OrderItem next = order.items().iterator().next();

        order.changeItemQuantity(next.id(), new Quantity(5));

        Assertions.assertWith(order,
                o -> Assertions.assertThat(o.totalAmount()).isEqualTo(new Money("500.00")),
                o -> Assertions.assertThat(o.totalItems()).isEqualTo(new Quantity(5))
        );
    }
}