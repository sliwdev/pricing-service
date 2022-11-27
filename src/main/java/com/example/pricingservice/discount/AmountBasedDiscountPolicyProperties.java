package com.example.pricingservice.discount;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ConfigurationProperties("discounts.amount-based")
record AmountBasedDiscountPolicyProperties(
        @Min(1)
        Integer minAmount,

        @Min(1)
        @Max(99)
        Integer initialPercentage,

        @Min(1)
        @Max(99)
        Integer step,

        @Min(1)
        @Max(99)
        Integer maxPercentage) {
}