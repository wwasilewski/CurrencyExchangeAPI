package pl.sda.CurrencyExchangeAPI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CurrencyRateController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyRateController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/api/currency/latest/{base}/{target}")
    public CurrencyRateDto getLatestCurrencyRate(String base, String target) {
        return currencyExchangeService.getLatestCurrencyRate(base, target));
    }

    @GetMapping("/api/currency/history/{base}/{target}/{date}")
    public CurrencyRateDto getOldCurrencyRate(String base, String target, LocalDateTime date) {
        return currencyExchangeService.getOldCurrencyRate(base, target, date));
    }


//    @PostMapping
//    public String getLatestCurrencyRates() {
//        return null;
//    }
}
