package com.example.pricingservice.discount;

import java.math.BigDecimal;

class AmountBasedDiscountPolicy implements DiscountPolicy {

    private final Integer minAmount;

    private final Integer initialPercentage;

    private final Integer step;

    private final Integer maxPercentage;


    AmountBasedDiscountPolicy(AmountBasedDiscountPolicyProperties properties) {
        this.minAmount = properties.minAmount();
        this.initialPercentage = properties.initialPercentage();
        this.step = properties.step();
        this.maxPercentage = properties.maxPercentage();
    }

    @Override
    public double apply(DiscountFactors discountFactors) {
        var amount = discountFactors.amount();
        var initialPrice = discountFactors.initialUnitPrice();

        if (amount < minAmount) {
            return initialPrice.multiply(new BigDecimal(amount))
                    .doubleValue();
        }

        var value = initialPrice.setScale(2, ROUNDING_MODE);
        return value.multiply(new BigDecimal(amount))
                .multiply(new BigDecimal(100 - getPercentage(amount)))
                .divide(new BigDecimal(100), ROUNDING_MODE)
                .doubleValue();
    }

    private int getPercentage(int amount) {
        var percentage = initialPercentage + step * (amount - minAmount);
        if (percentage > maxPercentage) {
            percentage = maxPercentage;
        }
        return percentage;
    }
}