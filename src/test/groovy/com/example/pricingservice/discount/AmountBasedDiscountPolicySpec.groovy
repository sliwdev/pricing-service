package com.example.pricingservice.discount


import spock.lang.Specification

class AmountBasedDiscountPolicySpec extends Specification {

    AmountBasedDiscountPolicyProperties properties = new AmountBasedDiscountPolicyProperties(2, 20, 5, 50)

    AmountBasedDiscountPolicy policy = new AmountBasedDiscountPolicy(properties)

    void 'should apply'(int amount, double expectedResult) {
        given:
        def discountFactors = new DiscountFactors(BigDecimal.valueOf(value), amount)

        expect:
        policy.apply(discountFactors) == expectedResult

        where:
        value  | amount || expectedResult
        200.00 | 10     || 1000.00
        200.00 | 4      || 560.00
        //etc.
    }
}