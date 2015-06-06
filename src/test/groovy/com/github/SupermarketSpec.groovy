package com.github

import com.github.domain.Price
import com.github.domain.Product
import groovy.transform.CompileStatic
import spock.lang.Specification

@CompileStatic
class SupermarketSpec extends Specification {

    private static final Price FORTY_PENCE = new Price(BigDecimal.valueOf(0.40d))

    def 'One apple should cost 40p'() {
        given: 'an apple'
        Product apple = new Product(FORTY_PENCE)

        when: 'calculating the price'
        Price price = apple.calculatePrice()

        then: 'the price should be 40p'
        price == FORTY_PENCE
    }

}
