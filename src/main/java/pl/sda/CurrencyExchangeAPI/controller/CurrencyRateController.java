package pl.sda.CurrencyExchangeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;
import pl.sda.CurrencyExchangeAPI.service.CurrencyExchangeService;

import java.time.LocalDateTime;

@RestController
public class CurrencyRateController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyRateController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/api/currency/latest/{base}/{target}")
    public CurrencyRateDto getLatestCurrencyRate(@PathVariable String base,
                                                 @PathVariable String target) {
        return currencyExchangeService.getLatestCurrencyRate(base, target);
    }

    @GetMapping("/api/currency/history/{base}/{target}/{date}")
    public CurrencyRateDto getOldCurrencyRate(@PathVariable String base,
                                              @PathVariable String target,
                                              @PathVariable LocalDateTime date) {
        return currencyExchangeService.getOldCurrencyRate(base, target, date);
    }


    //TODO
    @GetMapping("/api/gold/latest/{todo}")
    public CurrencyRateDto getLatestGoldPrice() {
        return null;
    }

    //TODO
    @GetMapping("/api/gold/history/{todo}")
    public CurrencyRateDto getOldGoldPrice() {
        return null;
    }


}
