package com.github.discount

import com.github.domain.Price
import com.github.domain.discount.ThreeForOnePoundDiscount
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ThreeForOnePoundDiscountSpec extends Specification {

    private static final Price FORTY_PENCE = new Price(BigDecimal.valueOf(0.40d))
    private static final Price EIGHTY_PENCE = new Price(BigDecimal.valueOf(0.80d))
    private static final Price ONE_POUND = new Price(BigDecimal.ONE)
    private static final Price ONE_POUND_FORTY_PENCE = new Price(BigDecimal.valueOf(1.40d))
    private static final int QUANTITY = 1

    @Shared
    ThreeForOnePoundDiscount threeForOnePoundDiscount = new ThreeForOnePoundDiscount()

    @Unroll('Price should be #expectedPrice when quantity is #quantity')
    def 'Apply three for £1 discount correctly'() {
        when: 'applying the discount'
        Price price = threeForOnePoundDiscount.apply(FORTY_PENCE, quantity)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        quantity | expectedPrice
        1        | FORTY_PENCE
        2        | EIGHTY_PENCE
        3        | ONE_POUND
        4        | ONE_POUND_FORTY_PENCE
    }

    def 'Throw NullPointerException if price is null'() {
        when: 'applying the discount'
        threeForOnePoundDiscount.apply(null, QUANTITY)

        then: 'a NullPointerException should be thrown'
        thrown NullPointerException
    }

    @Unroll('Throw IllegalArgumentException if quantity is less than 1 [#quantity]')
    def 'Throw IllegalArgumentException if quantity is less than 1'() {
        when: 'applying the discount'
        threeForOnePoundDiscount.apply(FORTY_PENCE, quantity)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        quantity << [0, -1]
    }

}
