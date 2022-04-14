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

    private CurrencyRepository currencyRepository;
    private CurrencyMapper currencyMapper;
    private ExchangerateReader exchangerateReader;

    @Autowired
    public CurrencyExchangeService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper,
                                   ExchangerateReader exchangerateReader) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.exchangerateReader = exchangerateReader;
    }

    //TODO
    public CurrencyRateDto getLatestCurrencyRate(String base, String target) {

        //get currency to process from api
        RateValue rateValue = exchangerateReader.getLatestRates(base, target);
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(base);
        currencyRate.setTarget(target);
        currencyRate.setRate(rateValue.getRates().get(target.toUpperCase()));
        currencyRate.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        //save currency to db
        currencyRepository.save(currencyRate);
        //map currency to currencyDto
        CurrencyRateDto currencyRateDto = currencyMapper.map(currencyRate);

        return currencyRateDto;
    }

    //TODO
    public CurrencyRateDto getOldCurrencyRate(String base, String target, LocalDateTime date) {

        //get old currency to process from api
        RateValue rateValue = exchangerateReader.getHistoryRates(base, target, date);
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(base);
        currencyRate.setTarget(target);
        currencyRate.setRate(rateValue.getRates().get(target.toUpperCase()));
        currencyRate.setDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        //save currency to db
        currencyRepository.save(currencyRate);
        //map currency to currencyDto
        CurrencyRateDto currencyRateDto = currencyMapper.map(currencyRate);

        return currencyRateDto;
    }
}
