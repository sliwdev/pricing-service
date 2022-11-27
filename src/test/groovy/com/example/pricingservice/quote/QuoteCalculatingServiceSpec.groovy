package com.example.pricingservice.quote

import com.example.pricingservice.discount.DiscountPolicy
import com.example.pricingservice.price.PriceQueryService
import com.example.pricingservice.quote.Quote
import com.example.pricingservice.quote.QuoteCalculatingService
import spock.lang.Specification

import java.time.Instant

import static com.example.pricingservice.price.PriceFixture.somePrice
import static com.example.pricingservice.price.PriceQueryService.SUPPORTED_CURRENCY
import static java.time.Instant.now
import static java.util.UUID.randomUUID

class QuoteCalculatingServiceSpec extends Specification {

    PriceQueryService priceQueryService = Mock(PriceQueryService.class)
    List<DiscountPolicy> policies = new ArrayList<>()
    QuoteCalculatingService service = new QuoteCalculatingService(priceQueryService, policies)

    UUID productId = randomUUID()
    int amount = 10
    Instant calculatedFor = now()

    void 'should apply highest available discount'() {
        given:
        policies << Mock(DiscountPolicy) { apply(_) >> 100 }
        policies << Mock(DiscountPolicy) { apply(_) >> 200 }
        policies << Mock(DiscountPolicy) { apply(_) >> 300 }
        priceQueryService.findOne(productId, calculatedFor) >> Optional.of(somePrice())

        Quote expected = new Quote(productId, 100, SUPPORTED_CURRENCY, calculatedFor, now())

        when:
        Optional<Quote> result = service.calculateQuote(productId, amount, calculatedFor)

        then:
        result.isPresent()
        result.get().value() == expected.value()
        result.get().currencyCode() == expected.currencyCode()
        result.get().calculatedFor() == expected.calculatedFor()
    }

    void 'should return empty'() {
        given:
        priceQueryService.findOne(_, _) >> Optional.empty()

        expect:
        service.calculateQuote(productId, amount, calculatedFor).isEmpty()
    }

    void 'should apply no discount when no discount available'() {
        given:
        priceQueryService.findOne(productId, calculatedFor) >> Optional.of(somePrice())
        Quote expected = new Quote(productId, 100, SUPPORTED_CURRENCY, calculatedFor, now())

        when:
        Optional<Quote> result = service.calculateQuote(productId, amount, calculatedFor)

        then:
        result.isPresent()
        result.get().value() == 10000
        result.get().currencyCode() == expected.currencyCode()
        result.get().calculatedFor() == expected.calculatedFor()
    }
}
