package com.example.pricingservice.discount

import com.example.pricingservice.discount.DiscountFactors
import com.example.pricingservice.discount.PercentageBasedDiscountPolicy
import spock.lang.Specification

class PercentageBasedDiscountPolicySpec extends Specification {

    PercentageBasedDiscountPolicy policy = new PercentageBasedDiscountPolicy(50)

    void 'should apply'() {
        given:
        DiscountFactors discountFactors = new DiscountFactors(BigDecimal.valueOf(200.00), 4)

        expect:
        policy.apply(discountFactors) == 400
    }
}
