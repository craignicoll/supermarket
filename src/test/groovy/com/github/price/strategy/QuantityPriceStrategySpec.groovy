package com.github.price.strategy

import com.github.price.Price
import com.github.price.discount.Discount
import spock.lang.Specification
import spock.lang.Unroll

class QuantityPriceStrategySpec extends Specification {

    private static final Price ONE_POUND_TWENTY_FIVE_PENCE = new Price(new BigDecimal("1.25"))
    private static final Price TWO_POUND_FIFTY_PENCE = new Price(new BigDecimal("2.50"))
    private static final Price THREE_POUND_SEVENTY_FIVE_PENCE = new Price(new BigDecimal("3.75"))

    PriceStrategy quantityPriceStrategy = new QuantityPriceStrategy(ONE_POUND_TWENTY_FIVE_PENCE)

    @Unroll('Price should be #expectedPrice when quantity is #quantity')
    def 'Calculate price by quantity'() {
        when: 'calculating the price'
        Price price = quantityPriceStrategy.calculate(quantity)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        quantity | expectedPrice
        1        | ONE_POUND_TWENTY_FIVE_PENCE
        2        | TWO_POUND_FIFTY_PENCE
        3        | THREE_POUND_SEVENTY_FIVE_PENCE
    }

    @Unroll('Throw IllegalArgumentException if quantity is less than 1 [#quantity]')
    def 'Throw IllegalArgumentException if quantity is less than 1'() {
        when: 'calculating price for quantity less than 1'
        quantityPriceStrategy.calculate(quantity)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        quantity << [0, -1, -2]
    }

    def 'Use discount if there is one'() {
        given: 'a discount'
        Discount discount = Mock(Discount)

        and: 'a quantity price strategy'
        PriceStrategy quantityPriceStrategy = new QuantityPriceStrategy(ONE_POUND_TWENTY_FIVE_PENCE, discount)

        when: 'calculating the price'
        Price price = quantityPriceStrategy.calculate(3)

        then: 'the price should be correct'
        price == TWO_POUND_FIFTY_PENCE

        1 * discount.apply(ONE_POUND_TWENTY_FIVE_PENCE, 3) >> TWO_POUND_FIFTY_PENCE
    }

}
