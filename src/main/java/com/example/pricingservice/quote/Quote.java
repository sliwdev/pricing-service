package com.example.pricingservice.quote;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.ReflectiveAccess;

import java.time.Instant;
import java.util.UUID;

@ReflectiveAccess
record Quote(
        UUID productId,
        double value,
        String currencyCode,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Instant calculatedFor,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Instant calculatedAt
) {
}
