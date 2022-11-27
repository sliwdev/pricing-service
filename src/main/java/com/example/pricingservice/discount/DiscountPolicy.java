package com.example.pricingservice.discount;

import java.math.RoundingMode;

public interface DiscountPolicy {

    RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    int SCALE = 2;

    double apply(DiscountFactors discountFactors);
}
