package com.jaenyeong.javacurrencyexchange.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateController {

    @GetMapping("/")
    public String main() {
        return "exchange-rate";
    }
}
