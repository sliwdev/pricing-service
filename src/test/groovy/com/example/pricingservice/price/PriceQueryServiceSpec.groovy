package com.example.pricingservice.price


import spock.lang.Specification

import java.time.Instant

import static PriceFixture.somePrice
import static com.example.pricingservice.price.PriceQueryService.SUPPORTED_CURRENCY
import static java.time.Instant.now
import static java.util.UUID.randomUUID

class PriceQueryServiceSpec extends Specification {

    PriceRepository priceRepository = Mock(PriceRepository.class)

    PriceQueryService service = new PriceQueryService(priceRepository)

    UUID productId = randomUUID()
    Instant calculatedFor = now()

    void 'should find one'() {
        given:
        Price somePrice = somePrice()
        priceRepository.findOneByProductIdAndCurrencyCodeAndValidFromAndValidTo(
                productId, SUPPORTED_CURRENCY, calculatedFor) >> Optional.of(somePrice)

        expect:
        service.findOne(productId, calculatedFor).get() == somePrice
    }

    void 'should return empty'() {
        given:
        priceRepository.findOneByProductIdAndCurrencyCodeAndValidFromAndValidTo(_, _, _) >> Optional.empty()

        expect:
        service.findOne(productId, calculatedFor).isEmpty()
    }
}
