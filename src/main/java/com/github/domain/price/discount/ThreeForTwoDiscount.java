package com.github.domain.price.discount;

import com.github.domain.price.Price;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class ThreeForTwoDiscount implements Discount {

    private static final int DISCOUNT_QUALIFICATION_QUANTITY = 3;

    @Override
    public Price apply(final Price price, final int quantity) {
        Objects.requireNonNull(price, "A price must be provided to apply a discount.");
        Preconditions.checkArgument(quantity >= 1, "Discount can only be applied for 1 or more products. Tried with [%s].", quantity);
        final Price priceBeforeDiscount = price.multiplyBy(quantity);
        return quantity >= DISCOUNT_QUALIFICATION_QUANTITY
                ? price.multiplyBy(quantity / DISCOUNT_QUALIFICATION_QUANTITY).subtractFrom(priceBeforeDiscount)
                : priceBeforeDiscount;
    }

}
