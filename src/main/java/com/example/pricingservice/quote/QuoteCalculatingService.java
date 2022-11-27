package com.example.pricingservice.quote;

import com.example.pricingservice.discount.DiscountFactors;
import com.example.pricingservice.discount.DiscountPolicy;
import com.example.pricingservice.price.PriceQueryService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.Instant.now;

public class QuoteCalculatingService {

    private final PriceQueryService priceQueryService;

    private final List<DiscountPolicy> policies;

    public QuoteCalculatingService(PriceQueryService priceQueryService, List<DiscountPolicy> policies) {
        this.priceQueryService = priceQueryService;
        this.policies = policies;
    }

    Optional<Quote> calculateQuote(UUID productId, int amount, Instant calculatedFor) {
        return priceQueryService.findOne(productId, calculatedFor)
                .map(price -> {
                    String currencyCode = price.getCurrencyCode();
                    double quoteValue = policies.stream()
                            .mapToDouble(policy -> policy.apply(new DiscountFactors(
                                    BigDecimal.valueOf(price.getValue()), amount)))
                            .min()
                            .orElse(price.getValue() * amount);
                    return new Quote(productId, quoteValue, currencyCode, calculatedFor, now());
                });
    }
}
