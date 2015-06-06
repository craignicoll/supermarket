package com.github.domain;

import java.math.BigDecimal;

public final class Price {

    private final BigDecimal amount;

    public Price(final BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        return amount.equals(price.amount);

    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
