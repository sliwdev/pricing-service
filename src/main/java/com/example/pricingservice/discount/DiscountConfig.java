package com.example.pricingservice.discount;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Factory
class DiscountConfig {

    @Singleton
    @Requires(property = "discounts.percentage-based.enabled", value = "true")
    PercentageBasedDiscountPolicy percentageBasedDiscountPolicy(
            @Value("${discounts.percentage-based.percentage}") Integer percentage) {
        return new PercentageBasedDiscountPolicy(percentage);
    }

    @Singleton
    AmountBasedDiscountPolicy amountBasedDiscountPolicy(
            AmountBasedDiscountPolicyProperties properties) {
        return new AmountBasedDiscountPolicy(properties);
    }
}
