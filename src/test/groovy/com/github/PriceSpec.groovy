package com.github

import com.github.domain.Price
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class PriceSpec extends Specification {

    @Shared
    Price price = new Price(BigDecimal.ONE)

    @Unroll('Correctly add prices #scenario')
    def 'Correctly add prices '() {
        when: 'adding prices'
        Price total = price.addTo(new Price(amount))

        then: 'the total should be correct'
        total == new Price(expectedTotal)

        where:
        scenario  | amount          | expectedTotal
        '1+0=1'   | BigDecimal.ZERO | BigDecimal.ONE
        '1+1=2'   | BigDecimal.ONE  | BigDecimal.valueOf(2)
        '1+10=11' | BigDecimal.TEN  | BigDecimal.valueOf(11)
    }

    @Unroll('Throw NullPointerException if attempting to add to null')
    def 'Throw NullPointerException if attempting to add to null'() {
        when: 'adding to null'
        price.addTo(null)

        then: 'a NullPointerException should be thrown'
        thrown NullPointerException
    }

    @Unroll('Correctly multiply prices #scenario')
    def 'Correctly multiply prices'() {
        when: 'multiplying prices'
        Price total = price.multiply(multiplier)

        then: 'the total should be correct'
        total == new Price(expectedTotal)

        where:
        scenario | multiplier | expectedTotal
        '1*0=0'  | 0          | BigDecimal.ZERO
        '1*1=1'  | 1          | BigDecimal.ONE
        '1*2=2'  | 2          | BigDecimal.valueOf(2)
    }

    @Unroll('Throw IllegalArgumentException if multiplying with #multiplier')
    def 'Throw IllegalArgumentException if multiplying with negative number'() {
        when: 'multiplying with negative number'
        price.multiply(multiplier)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        multiplier << [-1, -2, -3]
    }

}
