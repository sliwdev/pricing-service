package com.example.pricingservice.quote;

import com.example.pricingservice.discount.DiscountPolicy;
import com.example.pricingservice.price.PriceQueryService;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

import java.util.List;

@Factory
class QuoteConfig {

    @Singleton
    QuoteCalculatingService quoteCalculatingService(PriceQueryService priceQueryService,
                                                    List<DiscountPolicy> policies) {
        return new QuoteCalculatingService(priceQueryService, policies);
    }
}
