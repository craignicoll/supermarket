package com.github.domain.price.strategy;

import com.github.domain.price.Price;
import com.github.domain.price.discount.Discount;
import com.google.common.base.Preconditions;

import java.util.Objects;
import java.util.Optional;

abstract class AbstractPriceStrategy {

    private final Price price;
    private final Optional<Discount> discount;

    public AbstractPriceStrategy(final Price price) {
        Objects.requireNonNull(price, "Price cannot be null.");
        this.price = price;
        this.discount = Optional.empty();
    }

    public AbstractPriceStrategy(final Price price, final Discount discount) {
        Objects.requireNonNull(price, "Price cannot be null.");
        Objects.requireNonNull(discount, "Discount cannot be null.");
        this.price = price;
        this.discount = Optional.of(discount);
    }

    Price calculate(final int quantity, final PriceCalculator priceCalculator) {
        Preconditions.checkArgument(quantity >= 1, "Price can only be calculated for 1 or more products. Tried with [%s].", quantity);
        Objects.requireNonNull(priceCalculator, "Price calculator cannot be null.");
        return discount.isPresent()
                ? discount.get().apply(price, quantity)
                : priceCalculator.calculate(price, quantity);
    }

}
