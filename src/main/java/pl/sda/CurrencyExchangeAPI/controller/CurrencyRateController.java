package pl.sda.CurrencyExchangeAPI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRateController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyRateController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/api/currency/latest")
    public CurrencyRateDto getLatestCurrencyRate() {
        return currencyExchangeService.getLatestCurrencyRate();
    }

    @GetMapping("/api/currency/history")
    public CurrencyRateDto getOldCurrencyRate() {
        return currencyExchangeService.getOldCurrencyRate();
    }


//    @PostMapping
//    public String getLatestCurrencyRates() {
//        return null;
//    }
}
