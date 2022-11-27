package com.example.pricingservice.price;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class PriceRepository {

    static final Instant START = Instant.ofEpochSecond(1669498147);
    static final Instant END = Instant.ofEpochSecond(1701034147);
    public static final UUID SAMPLE_PRODUCT_ID = UUID.fromString("490f7b77-ecc5-44df-9474-5b119654b65f");

    List<Price> samplePrices = List.of(
            new Price(SAMPLE_PRODUCT_ID, 200.00, "PLN", START, END)
    );

    public Optional<Price> findOneByProductIdAndCurrencyCodeAndValidFromAndValidTo(UUID productId, String currencyCode, Instant calculatedFor) {
        return samplePrices.stream()
                .filter(price -> price.getId().getProductId().equals(productId)
                        && price.getId().getCurrencyCode().equals(currencyCode)
                        && !price.getId().getValidFrom().isAfter(calculatedFor)
                        && !price.getId().getValidTo().isBefore(calculatedFor))
                .findAny();
    }
}
