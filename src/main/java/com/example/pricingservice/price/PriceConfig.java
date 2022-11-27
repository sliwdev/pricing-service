package com.example.pricingservice.price;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
class PriceConfig {

    @Singleton
    PriceRepository priceRepository() {
        return new PriceRepository();
    }

    @Singleton
    PriceQueryService priceQueryService(PriceRepository priceRepository) {
        return new PriceQueryService(priceRepository);
    }
}
