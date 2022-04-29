package pl.sda.CurrencyExchangeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;
import pl.sda.CurrencyExchangeAPI.service.CurrencyExchangeService;

@RestController
@RequestMapping("/api/gold")
public class GoldExchangeRateController {

    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    public GoldExchangeRateController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/latest")
    public CurrencyRateDto getLatestGoldPrice() {
        return currencyExchangeService.getLatestGoldRate();
    }

    @GetMapping("/history/{date}")
    public CurrencyRateDto getOldGoldPrice(@PathVariable String date) {
        return currencyExchangeService.getOldGoldRate(date);
    }
}
