package com.github.domain;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public final class Product {

    private static final int DISCOUNT_QUALIFICATION_QUANTITY = 3;
    private static final Price ONE_POUND = new Price(BigDecimal.ONE);
    private static final Price ZERO = new Price(BigDecimal.ZERO);

    private final Price price;

    public Product(final Price price) {
        this.price = price;
    }

    public Price calculatePrice(final int quantity) {
        Preconditions.checkArgument(quantity >= 1, "Price can only be calculated for 1 or more products. Tried with [%s].", quantity);
        if (quantity < DISCOUNT_QUALIFICATION_QUANTITY) {
            return calculatePriceForQuantity(quantity);
        } else if (quantity == DISCOUNT_QUALIFICATION_QUANTITY) {
            return ONE_POUND;
        }
        return ONE_POUND.addTo(calculatePrice(quantity - DISCOUNT_QUALIFICATION_QUANTITY));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return price.equals(product.price);

    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }

    private Price calculatePriceForQuantity(final int quantity) {
        return IntStream.iterate(1, i -> i + 1).limit(quantity).mapToObj(i -> price).reduce(ZERO, (p1, p2) -> p1.addTo(p2));
    }

}
