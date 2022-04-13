package pl.sda.CurrencyExchangeAPI.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NbpController {

    @GetMapping
    public String getLatestCurrencyRates() {
        //zwrócić latest currency
        return null;
    }
}
