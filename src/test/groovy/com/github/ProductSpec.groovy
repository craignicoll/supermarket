package com.github

import com.github.domain.Product
import com.github.domain.price.Price
import com.github.domain.price.discount.ThreeForOnePoundDiscount
import com.github.domain.price.discount.ThreeForTwoDiscount
import com.github.domain.price.strategy.QuantityPriceStrategy
import com.github.domain.price.strategy.WeightPriceStrategy
import spock.lang.Specification
import spock.lang.Unroll

class ProductSpec extends Specification {

    private static final Price FORTY_PENCE = new Price(new BigDecimal("0.40"))
    private static final Price SEVENTY_PENCE = new Price(new BigDecimal("0.70"))
    private static final Price EIGHTY_PENCE = new Price(new BigDecimal("0.80"))
    private static final Price ONE_POUND = new Price(BigDecimal.ONE)
    private static final Price ONE_POUND_TWENTY_FIVE_PENCE = new Price(new BigDecimal("1.25"))
    private static final Price ONE_POUND_FORTY_PENCE = new Price(new BigDecimal("1.40"))
    private static final Price ONE_POUND_EIGHTY_PENCE = new Price(new BigDecimal("1.80"))
    private static final Price ONE_POUND_NINETY_NINE_PENCE = new Price(new BigDecimal("1.99"))
    private static final Price TWO_POUND = new Price(BigDecimal.valueOf(2))
    private static final Price TWO_POUND_TEN_PENCE = new Price(new BigDecimal("2.10"))
    private static final Price TWO_POUND_FIFTY_PENCE = new Price(new BigDecimal("2.50"))
    private static final Price TWO_POUND_EIGHTY_PENCE = new Price(new BigDecimal("2.80"))
    private static final Price TWO_POUND_NINETY_NINE_PENCE = new Price(new BigDecimal("2.99"))
    private static final Price THREE_POUND_SEVENTY_FIVE_PENCE = new Price(new BigDecimal("3.75"))
    private static final int ONE_HUNDRED_GRAMS = 100

    @Unroll('Price of #quantity apples should be #expectedPrice')
    def 'Calculate correct price of apples'() {
        given: 'an apple product'
        Product apple = new Product("apple", new QuantityPriceStrategy(FORTY_PENCE, new ThreeForOnePoundDiscount()))

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
        5        | ONE_POUND_EIGHTY_PENCE
        6        | TWO_POUND
    }

    @Unroll('Price of #quantity loaves of bread should be #expectedPrice')
    def 'Calculate correct price of bread'() {
        given: 'a bread product'
        Product bread = new Product("bread", new QuantityPriceStrategy(ONE_POUND_TWENTY_FIVE_PENCE))

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

    @Unroll('Price of #weight grams of cheese should be #expectedPrice')
    def 'Calculate correct price of cheese'() {
        given: 'a cheese product'
        Product cheese = new Product("cheese", new WeightPriceStrategy(ONE_POUND_NINETY_NINE_PENCE, ONE_HUNDRED_GRAMS))

        when: 'calculating the price'
        Price price = cheese.calculatePrice(weight)

        then: 'the price should be correct'
        price == expectedPrice

        where:
        weight | expectedPrice
        50     | ONE_POUND
        100    | ONE_POUND_NINETY_NINE_PENCE
        150    | TWO_POUND_NINETY_NINE_PENCE
    }

    @Unroll('Price of #quantity fizzy drinks should be #expectedPrice')
    def 'Calculate correct price of fizzy drinks'() {
        given: 'a fizzy drink'
        Product fizzyDrink = new Product("fizzy drink", new QuantityPriceStrategy(SEVENTY_PENCE, new ThreeForTwoDiscount()))

        when: 'calculating the price'
        Price price = fizzyDrink.calculatePrice(quantity)

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

}
