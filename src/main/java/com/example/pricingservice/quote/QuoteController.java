package com.example.pricingservice.quote;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import javax.validation.constraints.Min;
import java.util.UUID;

import static java.time.Instant.now;

@Controller("/quotes")
class QuoteController {

    private final QuoteCalculatingService quoteCalculatingService;

    QuoteController(QuoteCalculatingService quoteCalculatingService) {
        this.quoteCalculatingService = quoteCalculatingService;
    }

    @Get
    HttpResponse<Quote> getQuote(@QueryValue UUID productId,
                                 @QueryValue @Min(1) Integer amount) {
        return quoteCalculatingService.calculateQuote(productId, amount, now())
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}