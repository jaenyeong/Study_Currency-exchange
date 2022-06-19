package com.jaenyeong.javacurrencyexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateController {

    @GetMapping("/")
    public String mainPage() {
        return "exchange-rate";
    }
}
