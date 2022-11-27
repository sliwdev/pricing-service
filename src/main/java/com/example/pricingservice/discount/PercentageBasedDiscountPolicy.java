package com.example.pricingservice.discount;

import java.math.BigDecimal;

class PercentageBasedDiscountPolicy implements DiscountPolicy {

    private final Integer percentage;

    PercentageBasedDiscountPolicy(Integer percentage) {
        this.percentage = percentage;
    }

    @Override
    public double apply(DiscountFactors discountFactors) {
        return discountFactors.initialUnitPrice().setScale(SCALE, ROUNDING_MODE)
                .multiply(new BigDecimal(discountFactors.amount()))
                .multiply(new BigDecimal(100 - percentage))
                .divide(new BigDecimal(100), ROUNDING_MODE)
                .doubleValue();
    }
}
