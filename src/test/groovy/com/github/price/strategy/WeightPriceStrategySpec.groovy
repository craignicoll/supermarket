package com.github.price.strategy

import com.github.price.Price
import com.github.price.discount.Discount
import spock.lang.Specification
import spock.lang.Unroll

class WeightPriceStrategySpec extends Specification {

    private static final Price ONE_POUND = new Price(BigDecimal.ONE)
    private static final Price ONE_POUND_NINETY_NINE_PENCE = new Price(new BigDecimal("1.99"))
    private static final Price TWO_POUND_NINETY_NINE_PENCE = new Price(new BigDecimal("2.99"))
    private static final int ONE_HUNDRED_GRAMS = 100
    private static final int TWO_HUNDRED_GRAMS = 200

    PriceStrategy weightPriceStrategy = new WeightPriceStrategy(ONE_POUND_NINETY_NINE_PENCE, ONE_HUNDRED_GRAMS)

    @Unroll('Price should be #expectedPrice when weight is #weight')
    def 'Calculate price by weight'() {
        when: 'calculating the price'
        Price price = weightPriceStrategy.calculate(weight)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        weight | expectedPrice
        50     | ONE_POUND
        100    | ONE_POUND_NINETY_NINE_PENCE
        150    | TWO_POUND_NINETY_NINE_PENCE
    }

    @Unroll('Throw IllegalArgumentException if calculating price with weight less than 0 grams [#weight]')
    def 'Throw IllegalArgumentException if calculating price with weight less than 0 grams'() {
        when: 'calculating price with weight less than 0 grams'
        weightPriceStrategy.calculate(weight)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        weight << [-1, -2, -3]
    }

    def 'Use discount if there is one'() {
        given: 'a discount'
        Discount discount = Mock(Discount)

        and: 'a weight price strategy'
        PriceStrategy weightPriceStrategy = new WeightPriceStrategy(ONE_POUND_NINETY_NINE_PENCE, ONE_HUNDRED_GRAMS, discount)

        when: 'calculating the price'
        Price price = weightPriceStrategy.calculate(TWO_HUNDRED_GRAMS)

        then: 'the price should be correct'
        price == TWO_POUND_NINETY_NINE_PENCE

        1 * discount.apply(ONE_POUND_NINETY_NINE_PENCE, TWO_HUNDRED_GRAMS) >> TWO_POUND_NINETY_NINE_PENCE
    }

}
