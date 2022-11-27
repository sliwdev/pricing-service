package com.example.pricingservice.quote

import com.example.pricingservice.quote.Quote
import com.example.pricingservice.quote.QuoteController
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

import static com.example.pricingservice.price.PriceRepository.SAMPLE_PRODUCT_ID

@MicronautTest
class QuoteControllerSpec extends Specification {

    @Inject
    QuoteController quoteController

    @Inject
    ObjectMapper objectMapper

    void 'should get quote'() {
        given:
        Quote expected = objectMapper.readValue("""
                {
                    "productId":"490f7b77-ecc5-44df-9474-5b119654b65f",
                    "value":700.0,
                    "currencyCode":"PLN",
                    "calculatedFor":"2022-11-26T21:30:46.887609Z",
                    "calculatedAt":"2022-11-26T21:30:46.890860Z"
                }""", Quote.class)

        when:
        HttpResponse<Quote> result = quoteController.getQuote(SAMPLE_PRODUCT_ID, 5)

        then:
        result.status() == HttpStatus.OK
        result.body().value() == expected.value()
        result.body().productId() == expected.productId()
        result.body().currencyCode() == expected.currencyCode()
        result.body().calculatedFor().isAfter(expected.calculatedFor())
        result.body().calculatedAt().isAfter(expected.calculatedAt())
    }
}
