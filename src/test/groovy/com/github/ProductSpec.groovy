package com.github

import com.github.domain.Price
import com.github.domain.Product
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ProductSpec extends Specification {

    private static final Price FORTY_PENCE = new Price(BigDecimal.valueOf(0.40d))
    private static final Price EIGHTY_PENCE = new Price(BigDecimal.valueOf(0.80d))
    private static final Price ONE_POUND = new Price(BigDecimal.ONE)
    private static final Price ONE_POUND_FORTY_PENCE = new Price(BigDecimal.valueOf(1.40d))

    @Shared
    Product apple = new Product(FORTY_PENCE)

    @Unroll('Calculate price for #quantity apples')
    def 'Apple prices should be calculated correctly'() {
        when: 'calculating the price'
        Price price = apple.calculatePrice(quantity)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        quantity | expectedPrice
        1        | FORTY_PENCE
        2        | EIGHTY_PENCE
        3        | ONE_POUND
        4        | ONE_POUND_FORTY_PENCE
    }

    @Unroll('Calculate price for #quantity apples')
    def 'IllegalArgumentException should be thrown when calculating apple prices for quantities less than 1'() {
        when: 'calculating the price'
        apple.calculatePrice(quantity)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        quantity << [0, -1]
    }

}
