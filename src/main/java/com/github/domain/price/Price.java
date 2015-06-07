package com.github.domain.price;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Price {

    private final BigDecimal amount;

    public Price(final BigDecimal amount) {
        Objects.requireNonNull(amount, "A price must have an amount.");
        this.amount = amount;
    }

    public Price addTo(final Price price) {
        Objects.requireNonNull(price, "Cannot add to null.");
        return new Price(amount.add(price.amount));
    }

    public Price multiplyBy(final int multiplier) {
        Preconditions.checkArgument(multiplier >= 0, "Cannot multiply with a negative number. Tried with [%s].", multiplier);
        return new Price(IntStream.iterate(1, i -> i + 1).limit(multiplier).mapToObj(i -> this.amount).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)).setScale(2, RoundingMode.HALF_UP));
    }

    public Price divideBy(final int divisor) {
        Preconditions.checkArgument(divisor >= 1, "Cannot divide with a number less than 1. Tried with [%s].", divisor);
        return new Price(amount.divide(BigDecimal.valueOf(divisor)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        return amount.compareTo(price.amount) == 0;

    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("amount", amount)
                .toString();
    }
}
