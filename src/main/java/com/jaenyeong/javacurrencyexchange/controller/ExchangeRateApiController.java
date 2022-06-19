package com.jaenyeong.javacurrencyexchange.controller;

import com.jaenyeong.javacurrencyexchange.domain.Currency;
import com.jaenyeong.javacurrencyexchange.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/exchange-rate/sources/{source}/targets/{target}")
    public ResponseEntity<String> getExchangeRate(
        @PathVariable final Currency source,
        @PathVariable final Currency target
    ) {
        return ResponseEntity.ok(exchangeRateService.getExchangeRate(source, target));
    }

    @GetMapping("/calculate/sources/{source}/targets/{target}")
    public ResponseEntity<String> calculateRemittance(
        @PathVariable final Currency source,
        @PathVariable final Currency target,
        @RequestParam double remittance
    ) {
        return ResponseEntity.ok(exchangeRateService.getCalculateRemittance(source, target, remittance));
    }
}
