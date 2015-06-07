package com.github.domain.product;

import com.github.domain.Price;
import com.github.domain.discount.Discount;

import java.util.Objects;
import java.util.Optional;

public final class ProductBuilder {

    private final Price price;
    private Discount discount;

    private ProductBuilder(final Price price) {
        this.price = price;
    }

    public ProductBuilder withDiscount(final Discount discount) {
        Objects.requireNonNull(discount, "A product cannot have null discount.");
        this.discount = discount;
        return this;
    }

    public static ProductBuilder builder(final Price price) {
        Objects.requireNonNull(price, "A product must have a price.");
        return new ProductBuilder(price);
    }

    public Product build() {
        return new Product(
                price,
                Optional.ofNullable(discount));
    }

}
