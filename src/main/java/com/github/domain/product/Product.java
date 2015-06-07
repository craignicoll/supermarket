package com.github.domain.product;

import com.github.domain.Price;
import com.github.domain.discount.Discount;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.Objects;
import java.util.Optional;

public final class Product {

    private final Price price;
    private final Optional<Discount> discount;

    Product(final Price price, final Optional<Discount> discount) {
        Objects.requireNonNull(price, "A product must have a price.");
        Objects.requireNonNull(discount, "An optional discount must be provided.");
        this.price = price;
        this.discount = discount;
    }

    public Price calculatePrice(final int quantity) {
        Preconditions.checkArgument(quantity >= 1, "Price can only be calculated for 1 or more products. Tried with [%s].", quantity);
        return discount.isPresent() ? discount.get().apply(price, quantity) : price.multiply(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!price.equals(product.price)) return false;
        return discount.equals(product.discount);

    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + discount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("price", price)
                .add("discount", discount)
                .toString();
    }

}
