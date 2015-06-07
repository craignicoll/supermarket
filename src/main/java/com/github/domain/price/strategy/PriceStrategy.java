package com.github.domain.price.strategy;

import com.github.domain.price.Price;

public interface PriceStrategy {

    Price calculate(int amountPurchased);

}
