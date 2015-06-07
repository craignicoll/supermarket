package com.github.price.discount

import com.github.price.Price
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ThreeForTwoDiscountSpec extends Specification {

    private static final Price SEVENTY_PENCE = new Price(new BigDecimal("0.70"))
    private static final Price ONE_POUND_FORTY_PENCE = new Price(new BigDecimal("1.40"))
    private static final Price TWO_POUND_TEN_PENCE = new Price(new BigDecimal("2.10"))
    private static final Price TWO_POUND_EIGHTY_PENCE = new Price(new BigDecimal("2.80"))
    private static final int QUANTITY = 1

    @Shared
    ThreeForTwoDiscount threeForTwoDiscount = new ThreeForTwoDiscount()

    @Unroll('Price should be #expectedPrice when quantity is #quantity')
    def 'Apply three-for-two discount correctly'() {
        when: 'applying the discount'
        Price price = threeForTwoDiscount.apply(SEVENTY_PENCE, quantity)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        quantity | expectedPrice
        1        | SEVENTY_PENCE
        2        | ONE_POUND_FORTY_PENCE
        3        | ONE_POUND_FORTY_PENCE
        4        | TWO_POUND_TEN_PENCE
        5        | TWO_POUND_EIGHTY_PENCE
        6        | TWO_POUND_EIGHTY_PENCE
    }

    def 'Throw NullPointerException if price is null'() {
        when: 'applying the discount'
        threeForTwoDiscount.apply(null, QUANTITY)

        then: 'a NullPointerException should be thrown'
        thrown NullPointerException
    }

    @Unroll('Throw IllegalArgumentException if quantity is less than 1 [#quantity]')
    def 'Throw IllegalArgumentException if quantity is less than 1'() {
        when: 'applying the discount'
        threeForTwoDiscount.apply(SEVENTY_PENCE, quantity)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        quantity << [0, -1, -2]
    }

}
