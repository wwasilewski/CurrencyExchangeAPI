package pl.sda.CurrencyExchangeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.CurrencyExchangeAPI.model.Currency;
import pl.sda.CurrencyExchangeAPI.model.StatisticsValue;
import pl.sda.CurrencyExchangeAPI.service.StatisticsService;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{base}/{target}/{dateFrom}/{dateTo}")
    public StatisticsValue getStatisticsForPeriod(@PathVariable String base,
                                                  @PathVariable String target,
                                                  @PathVariable String dateFrom,
                                                  @PathVariable String dateTo) {
        return statisticsService.getStatsForPeriod(base, target, dateFrom, dateTo);
    }

    @GetMapping("/db-all")
    public long getDbRecordsCount() {
        return statisticsService.getDbCount();
    }

    @GetMapping("/db-gold")
    public long getDbGoldRecordsCount() {
        return statisticsService.getDbCountForCurrency(Currency.GOLD.symbol).size();
    }

    @GetMapping("/currency/{base}")
    public long getDbCustomCurrencyRecordsCount(@PathVariable String base) {
        return statisticsService.getDbCountForCurrency(base).size();
    }
}
