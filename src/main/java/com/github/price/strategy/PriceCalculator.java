package com.github.price.strategy;

import com.github.price.Price;

public interface PriceCalculator {

    Price calculate(Price price, int amount);

}
