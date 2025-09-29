package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.Money;
import com.algaworks.algashop.ordering.domain.valueobject.ProductName;
import com.algaworks.algashop.ordering.domain.valueobject.Quantity;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class OrderTest {

    @Test
    void shouldGenerate() {
        Order.draft(new CustomerId());
    }

    @Test
    void shouldAddOrderItem() {
        Order order = Order.draft(new CustomerId());

        ProductId productId = new ProductId();

        order.addItem(
                productId,
                new ProductName("Mouse pad"),
                new Money("100"),
                new Quantity(1)
        );

        Assertions.assertThat(order.items()).isNotEmpty();
        Assertions.assertThat(order.items()).hasSize(1);

        Optional<OrderItem> first = order.items().stream().findFirst();

        Assertions.assertWith(first.get(),
                i -> Assertions.assertThat(i.productId()).isEqualTo(productId),
                i -> Assertions.assertThat(i.productName()).isEqualTo(new ProductName("Mouse pad")),
                i -> Assertions.assertThat(i.price()).isEqualTo(new Money("100")),
                i -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(1))
        );
    }
}