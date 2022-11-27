package com.example.pricingservice.price;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class PriceQueryService {

    static final String SUPPORTED_CURRENCY = "PLN";

    private final PriceRepository priceRepository;

    PriceQueryService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> findOne(UUID productId, Instant calculatedFor) {
        return priceRepository.findOneByProductIdAndCurrencyCodeAndValidFromAndValidTo(
                productId, SUPPORTED_CURRENCY, calculatedFor);
    }
}
