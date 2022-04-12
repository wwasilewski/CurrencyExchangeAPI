package pl.sda.CurrencyExchangeAPI.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NbpController {

    @PostMapping
    public String getLatestCurrencyRates() {
        return null;
    }
}
