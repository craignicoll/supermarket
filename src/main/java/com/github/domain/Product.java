package com.github.domain;

public final class Product {

    private final Price price;

    public Product(final Price price) {
        this.price = price;
    }

    public Price calculatePrice() {
        return price;
    }

}
