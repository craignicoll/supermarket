package com.github;

import com.github.price.Price;
import com.github.price.strategy.PriceStrategy;

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
