package com.algaworks.algashop.ordering.domain.valueobject;

import java.io.Serializable;
import java.util.Objects;

public record Quantity(Integer value) implements Serializable, Comparable<Quantity> {

    public static final Quantity ZERO = new Quantity(0);

    public Quantity {
        Objects.requireNonNull(value);

        if (value < 0) {
            throw new IllegalArgumentException();
        }
    }

    public Quantity add(Quantity quantity) {
        return new Quantity(this.value + quantity.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int compareTo(Quantity o) {
        return this.value.compareTo(o.value);
    }
}
