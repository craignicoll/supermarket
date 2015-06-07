package com.github.price

import com.github.domain.price.Price
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
        Price total = price.multiplyBy(multiplier)

        then: 'the total should be correct'
        total == new Price(expectedTotal)

        where:
        scenario | multiplier | expectedTotal
        '1*0=0'  | 0          | BigDecimal.ZERO
        '1*1=1'  | 1          | BigDecimal.ONE
        '1*2=2'  | 2          | BigDecimal.valueOf(2)
    }

    @Unroll('Throw IllegalArgumentException if multiplier is less than 0 [#multiplier]')
    def 'Throw IllegalArgumentException if multiplier is less than 0'() {
        when: 'multiplying with number less than 0'
        price.multiplyBy(multiplier)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        multiplier << [-1, -2, -3]
    }

    @Unroll('Correctly divide prices #scenario')
    def 'Correctly divide prices'() {
        given: 'a price'
        Price price = new Price(BigDecimal.TEN)

        when: 'dividing prices'
        Price total = price.divideBy(divisor)

        then: 'the total should be correct'
        total == new Price(expectedTotal)

        where:
        scenario  | divisor | expectedTotal
        '10/1=10' | 1       | BigDecimal.TEN
        '10/2=5'  | 2       | BigDecimal.valueOf(5)
        '10/5=2'  | 5       | BigDecimal.valueOf(2)
    }

    @Unroll('Throw IllegalArgumentException if divisor is less than 1 [#divisor]')
    def 'Throw IllegalArgumentException if dividing with number less than 1'() {
        when: 'dividing by number less than 1'
        price.divideBy(divisor)

        then: 'an IllegalArgumentException should be thrown'
        thrown IllegalArgumentException

        where:
        divisor << [0, -1, -2]
    }

    @Unroll('Correctly subtract prices #scenario')
    def 'Correctly subtract prices '() {
        given: 'a price'
        Price price = new Price(amountToSubtract)

        when: 'subtracting prices'
        Price total = price.subtractFrom(new Price(amountToSubtractFrom))

        then: 'the total should be correct'
        total == new Price(expectedTotal)

        where:
        scenario | amountToSubtract      | amountToSubtractFrom  | expectedTotal
        '1-0=1'  | BigDecimal.ZERO       | BigDecimal.ONE        | BigDecimal.ONE
        '3-2=1'  | BigDecimal.valueOf(2) | BigDecimal.valueOf(3) | BigDecimal.ONE
        '10-5=2' | BigDecimal.valueOf(5) | BigDecimal.TEN        | BigDecimal.valueOf(5)
    }

    @Unroll('Throw NullPointerException if attempting to subtract from null')
    def 'Throw NullPointerException if attempting to subtract from null'() {
        when: 'subtracting from null'
        price.subtractFrom(null)

        then: 'a NullPointerException should be thrown'
        thrown NullPointerException
    }

}
