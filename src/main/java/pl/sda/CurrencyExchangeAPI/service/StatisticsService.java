package pl.sda.CurrencyExchangeAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;
import pl.sda.CurrencyExchangeAPI.model.StatisticsValue;
import pl.sda.CurrencyExchangeAPI.repository.CurrencyRepository;

import java.util.List;

@Service
public class StatisticsService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateReader exchangerateReader;

    @Autowired
    public StatisticsService(CurrencyRepository currencyRepository, ExchangeRateReader exchangerateReader) {
        this.currencyRepository = currencyRepository;
        this.exchangerateReader = exchangerateReader;
    }

    public long getDbCount() {
        return currencyRepository.count();
    }

    public List<CurrencyRate> getDbCountForCurrency(String base) {
        return currencyRepository.findByBase(base.toUpperCase());
    }

    public StatisticsValue getStatsForPeriod(String base, String target, String dateFrom, String dateTo) {
        return exchangerateReader.getStatisticsForPeriod(base, target, dateFrom, dateTo);
    }
}
