package com.github.product

import com.github.domain.Price
import com.github.domain.discount.Discount
import com.github.domain.product.Product
import com.github.domain.product.ProductBuilder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ProductSpec extends Specification {

    private static final Price FORTY_PENCE = new Price(BigDecimal.valueOf(0.40d))
    private static final Price ONE_POUND_TWENTY_FIVE_PENCE = new Price(BigDecimal.valueOf(1.25d))
    private static final Price TWO_POUND_FIFTY_PENCE = new Price(BigDecimal.valueOf(2.50d))
    private static final Price THREE_POUND_SEVENTY_FIVE_PENCE = new Price(BigDecimal.valueOf(3.75d))
    private static final int QUANTITY = 1

    @Shared
    Product bread = ProductBuilder.builder(ONE_POUND_TWENTY_FIVE_PENCE).build()

    @Unroll('Throw IllegalArgumentException if quantity is less than 1 [#quantity]')
    def 'Throw IllegalArgumentException if quantity is less than 1'() {
        when: 'calculating the price'
        bread.calculatePrice(quantity)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        quantity << [0, -1]
    }

    @Unroll('Price should be #expectedPrice when quantity is #quantity')
    def 'Bread prices should be calculated correctly'() {
        when: 'calculating the price'
        Price price = bread.calculatePrice(quantity)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        quantity | expectedPrice
        1        | ONE_POUND_TWENTY_FIVE_PENCE
        2        | TWO_POUND_FIFTY_PENCE
        3        | THREE_POUND_SEVENTY_FIVE_PENCE
    }

    def 'Uses discount if there is one'() {
        given: 'a discount'
        Discount discount = Mock(Discount)

        and: 'an apple'
        Product apple = ProductBuilder.builder(FORTY_PENCE).withDiscount(discount).build()

        when: 'calculating the price'
        Price price = apple.calculatePrice(QUANTITY)

        then: 'the price should be correct'
        1 * discount.apply(FORTY_PENCE, QUANTITY) >> FORTY_PENCE
        price == FORTY_PENCE
    }

}
