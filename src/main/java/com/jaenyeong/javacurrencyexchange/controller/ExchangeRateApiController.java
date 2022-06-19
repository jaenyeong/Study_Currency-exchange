package com.jaenyeong.javacurrencyexchange.controller;

import com.jaenyeong.javacurrencyexchange.domain.Currency;
import com.jaenyeong.javacurrencyexchange.dto.RemittanceResponse;
import com.jaenyeong.javacurrencyexchange.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExchangeRateApiController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateApiController(final ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/sources/{source}/targets/{target}")
    public RemittanceResponse exchangeRate(
        @PathVariable final Currency source,
        @PathVariable final Currency target,
        @RequestParam(required = false) double remittance
    ) {
        return exchangeRateService.getExchangeRate(source, target, remittance);
    }
}
