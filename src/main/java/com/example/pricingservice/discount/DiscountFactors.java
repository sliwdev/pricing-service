package com.example.pricingservice.discount;

import java.math.BigDecimal;

public record DiscountFactors(BigDecimal initialUnitPrice, int amount) {

}