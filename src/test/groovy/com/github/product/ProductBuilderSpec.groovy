package com.github.product

import com.github.domain.Price
import com.github.domain.discount.Discount
import com.github.domain.product.Product
import com.github.domain.product.ProductBuilder
import spock.lang.Specification
import spock.lang.Unroll

class ProductBuilderSpec extends Specification {

    private static final Price PRICE = new Price(BigDecimal.ONE)

    def 'Build product correctly'() {
        given: 'a product builder'
        ProductBuilder productBuilder = ProductBuilder.builder(PRICE)

        when: 'building the product'
        Product product = productBuilder.build()

        then: 'the price should be correct'
        product.calculatePrice(BigDecimal.ONE.intValue()) == PRICE
    }

    @Unroll('Throw NullPointerException if #scenario is null')
    def 'Throw NullPointerException if any builder argument is null'() {
        when: 'building the product'
        ProductBuilder.builder(price).withDiscount(discount).build()

        then: 'a NullPointerException should be thrown'
        thrown NullPointerException

        where:
        scenario   | price | discount
        'discount' | PRICE | null
        'price'    | null  | Mock(Discount)
    }

}
