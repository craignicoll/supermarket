package com.github.price.strategy;

import com.github.price.Price;

public interface PriceStrategy {

    Price calculate(int amountPurchased);

}
