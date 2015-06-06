package com.github.domain;

import java.math.BigDecimal;

public final class Product {

    private final Price price;

    public Product(final Price price) {
        this.price = price;
    }

    public Price calculatePrice(final int quantity) {
        return quantity == 1 ? price : new Price(BigDecimal.ONE);
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
}
