package com.github.price.discount;

import com.github.price.Price;

public interface Discount {

    Price apply(Price price, int quantity);

}
