package com.github.domain;

import com.github.domain.price.Price;
import com.github.domain.price.strategy.PriceStrategy;

public final class Product {

    private final String name;
    private final PriceStrategy priceStrategy;

    public Product(final String name, final PriceStrategy priceStrategy) {
        this.name = name;
        this.priceStrategy = priceStrategy;
    }

    public Price calculatePrice(final int amount) {
        return priceStrategy.calculate(amount);
    }

}
