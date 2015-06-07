package com.github.domain.price.discount;

import com.github.domain.price.Price;

public interface Discount {

    Price apply(Price price, int quantity);

}
