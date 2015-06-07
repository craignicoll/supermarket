package com.github.domain.discount;

import com.github.domain.Price;

public interface Discount {

    Price apply(Price price, int quantity);

}
