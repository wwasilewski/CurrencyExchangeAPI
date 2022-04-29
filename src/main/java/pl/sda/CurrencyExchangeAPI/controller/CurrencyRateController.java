package pl.sda.CurrencyExchangeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;
import pl.sda.CurrencyExchangeAPI.model.StatisticsValue;
import pl.sda.CurrencyExchangeAPI.service.CurrencyExchangeService;

@RestController
public class CurrencyRateController {

    private final CurrencyExchangeService currencyExchangeService;
    private static final String GOLD_SYMBOL = "XAU";

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
                                              @PathVariable String date) {
        return currencyExchangeService.getOldCurrencyRate(base, target, date);
    }

    @GetMapping("/api/gold/latest")
    public CurrencyRateDto getLatestGoldPrice() {
        return currencyExchangeService.getLatestGoldRate();
    }

    @GetMapping("/api/gold/history/{date}")
    public CurrencyRateDto getOldGoldPrice(@PathVariable String date) {
        return currencyExchangeService.getOldGoldRate(date);
    }

    @GetMapping("/api/stats/all")
    public long getDbRecordsCount() {
        return currencyExchangeService.getDbCount();
    }

    @GetMapping("/api/stats/gold")
    public long getDbGoldRecordsCount() {
        return currencyExchangeService.getDbCountForCurrency(GOLD_SYMBOL).size();
    }

    @GetMapping("/api/stats/{base}")
    public long getDbCustomCurrencyRecordsCount(@PathVariable String base) {
        return currencyExchangeService.getDbCountForCurrency(base).size();
    }

    @GetMapping("/api/stats/{base}/{target}/{dateFrom}/{dateTo}")
    public StatisticsValue getStatisticsForPeriod(@PathVariable String base,
                                                  @PathVariable String target,
                                                  @PathVariable String dateFrom,
                                                  @PathVariable String dateTo) {
        return currencyExchangeService.getStatsForPeriod(base, target, dateFrom, dateTo);
    }
}
