package com.github

import com.github.domain.Price
import com.github.domain.Product
import spock.lang.Specification
import spock.lang.Unroll

class ProductSpec extends Specification {

    private static final Price FORTY_PENCE = new Price(BigDecimal.valueOf(0.40d))
    private static final Price ONE_POUND = new Price(BigDecimal.ONE)

    @Unroll('Calculate price for #scenario')
    def 'Apple prices should be calculated correctly'() {
        given: 'an apple'
        Product apple = new Product(FORTY_PENCE)

        when: 'calculating the price'
        Price price = apple.calculatePrice(quantity)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        scenario       | quantity | expectedPrice
        'one apple'    | 1        | FORTY_PENCE
        'three apples' | 3        | ONE_POUND
    }

}
