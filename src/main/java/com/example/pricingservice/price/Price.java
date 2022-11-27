package com.example.pricingservice.price;

import java.time.Instant;
import java.util.UUID;

public class Price {
    private final PriceId id;

    private final double value;

    Price(UUID productId, double value, String currencyCode, Instant validFrom, Instant validTo) {
        this.id = new PriceId(productId, currencyCode, validFrom, validTo);
        this.value = value;
    }

    public PriceId getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getCurrencyCode() {
        return id.getCurrencyCode();
    }

    static class PriceId {

        private final UUID productId;

        private final String currencyCode;

        private final Instant validFrom;

        private final Instant validTo;

        private PriceId(UUID productId, String currencyCode, Instant validFrom, Instant validTo) {
            this.productId = productId;
            this.currencyCode = currencyCode;
            this.validFrom = validFrom;
            this.validTo = validTo;
        }

        public UUID getProductId() {
            return productId;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public Instant getValidFrom() {
            return validFrom;
        }

        public Instant getValidTo() {
            return validTo;
        }
    }
}
