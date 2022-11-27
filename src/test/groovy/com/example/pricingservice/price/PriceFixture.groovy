package com.example.pricingservice.price


import static com.example.pricingservice.price.PriceQueryService.SUPPORTED_CURRENCY
import static com.example.pricingservice.price.PriceRepository.END
import static com.example.pricingservice.price.PriceRepository.START
import static java.util.UUID.randomUUID

class PriceFixture {

    static Price somePrice() {
        return new Price(randomUUID(), 1000.00, SUPPORTED_CURRENCY, START, END)
    }
}
