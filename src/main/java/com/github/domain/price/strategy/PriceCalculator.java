package com.github.domain.price.strategy;

import com.github.domain.price.Price;

public interface PriceCalculator {

    Price calculate(Price price, int amount);

}
