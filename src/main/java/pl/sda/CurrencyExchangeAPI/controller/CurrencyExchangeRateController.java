package pl.sda.CurrencyExchangeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;
import pl.sda.CurrencyExchangeAPI.service.CurrencyExchangeService;

@RestController
@RequestMapping("/api/currency")
public class CurrencyExchangeRateController {

    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeRateController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/latest/{base}/{target}")
    public CurrencyRateDto getLatestCurrencyRate(@PathVariable String base,
                                                 @PathVariable String target) {
        return currencyExchangeService.getLatestCurrencyRate(base, target);
    }

    @GetMapping("/history/{base}/{target}/{date}")
    public CurrencyRateDto getOldCurrencyRate(@PathVariable String base,
                                              @PathVariable String target,
                                              @PathVariable String date) {
        return currencyExchangeService.getOldCurrencyRate(base, target, date);
    }
}
