package pl.sda.CurrencyExchangeAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;
import pl.sda.CurrencyExchangeAPI.model.RateValue;
import pl.sda.CurrencyExchangeAPI.repository.CurrencyRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyExchangeService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final ExchangerateReader exchangerateReader;

    @Autowired
    public CurrencyExchangeService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper,
                                   ExchangerateReader exchangerateReader) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.exchangerateReader = exchangerateReader;
    }


    public CurrencyRateDto getLatestCurrencyRate(String base, String target) {
        RateValue rateValue = exchangerateReader.getLatestRates(base, target);

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(base);
        currencyRate.setTarget(target);
        currencyRate.setRate(rateValue.getRates().get(target.toUpperCase()));
        currencyRate.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        currencyRepository.save(currencyRate);

        return currencyMapper.map(currencyRate);
    }


    public CurrencyRateDto getOldCurrencyRate(String base, String target, LocalDateTime date) {
        RateValue rateValue = exchangerateReader.getHistoryRates(base, target, date);

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(base);
        currencyRate.setTarget(target);
        currencyRate.setRate(rateValue.getRates().get(target.toUpperCase()));
        currencyRate.setDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        currencyRepository.save(currencyRate);

        return currencyMapper.map(currencyRate);
    }


    public CurrencyRateDto getLatestGoldRate() {
        RateValue rateValue = exchangerateReader.getLatestGoldRates();

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase("XAU");
        currencyRate.setTarget("PLN");
        currencyRate.setRate(rateValue.getRates().get(currencyRate.getTarget()));
        currencyRate.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        currencyRepository.save(currencyRate);

        return currencyMapper.map(currencyRate);
    }

    public CurrencyRateDto getOldGoldRate(LocalDateTime date) {
        RateValue rateValue = exchangerateReader.getHistoryGoldRates(date);

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase("XAU");
        currencyRate.setTarget("PLN");
        currencyRate.setRate(rateValue.getRates().get(currencyRate.getTarget()));
        currencyRate.setDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        currencyRepository.save(currencyRate);

        return currencyMapper.map(currencyRate);
    }

}
