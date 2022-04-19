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

    private final String goldSymbol = "XAU";
    private final String zlotySymbol = "PLN";
    private final String dateFormat = "yyyy-MM-dd";

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final ExchangerateReader exchangerateReader;
    private final CurrencyExistValidation currencyExistValidation;

    @Autowired
    public CurrencyExchangeService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper,
                                   ExchangerateReader exchangerateReader, CurrencyExistValidation currencyExistValidation) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.exchangerateReader = exchangerateReader;
        this.currencyExistValidation = currencyExistValidation;
    }


    public CurrencyRateDto getLatestCurrencyRate(String base, String target) {
        if(currencyExistValidation.isCurrencyExisting(base) && currencyExistValidation.isCurrencyExisting(target)) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(base.toUpperCase());
        currencyRate.setTarget(target.toUpperCase());
        currencyRate.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat)));
        if (currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                currencyRate.getBase(),
                currencyRate.getTarget(),
                currencyRate.getDate()
                ) != null) {
            return currencyMapper.map(currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                    currencyRate.getBase(),
                    currencyRate.getTarget(),
                    currencyRate.getDate()));
        } else {
            RateValue rateValue = exchangerateReader.getLatestRates(base, target);
            currencyRate.setRate(rateValue.getRates().get(target.toUpperCase()));
            currencyRepository.save(currencyRate);
            return currencyMapper.map(currencyRate);
        }} else {
            throw
        }

    }


    public CurrencyRateDto getOldCurrencyRate(String base, String target, String date) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(base.toUpperCase());
        currencyRate.setTarget(target.toUpperCase());
        currencyRate.setDate(date);
        if (currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                currencyRate.getBase(),
                currencyRate.getTarget(),
                currencyRate.getDate()
        ) != null) {
            return currencyMapper.map(currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                    currencyRate.getBase(),
                    currencyRate.getTarget(),
                    currencyRate.getDate()));
        } else {
            RateValue rateValue = exchangerateReader.getHistoryRates(base, target, date);
            currencyRate.setRate(rateValue.getRates().get(target.toUpperCase()));
            currencyRepository.save(currencyRate);
            return currencyMapper.map(currencyRate);
        }
    }


    public CurrencyRateDto getLatestGoldRate() {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(goldSymbol);
        currencyRate.setTarget(zlotySymbol);
        currencyRate.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat)));
        if (currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                currencyRate.getBase(),
                currencyRate.getTarget(),
                currencyRate.getDate()
        ) != null) {
            return currencyMapper.map(currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                    currencyRate.getBase(),
                    currencyRate.getTarget(),
                    currencyRate.getDate()));
        } else {
            RateValue rateValue = exchangerateReader.getLatestGoldRates();
            currencyRate.setRate(rateValue.getRates().get(currencyRate.getTarget()));
            currencyRepository.save(currencyRate);
            return currencyMapper.map(currencyRate);
        }
    }

    public CurrencyRateDto getOldGoldRate(String date) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBase(goldSymbol);
        currencyRate.setTarget(zlotySymbol);
        currencyRate.setDate(date);
        if (currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                currencyRate.getBase(),
                currencyRate.getTarget(),
                currencyRate.getDate()
        ) != null) {
            return currencyMapper.map(currencyRepository.findCurrencyRateByBaseAndTargetAndDate(
                    currencyRate.getBase(),
                    currencyRate.getTarget(),
                    currencyRate.getDate()));
        } else {
            RateValue rateValue = exchangerateReader.getHistoryGoldRates(date);
            currencyRate.setRate(rateValue.getRates().get(currencyRate.getTarget()));
            currencyRepository.save(currencyRate);
            return currencyMapper.map(currencyRate);
        }
    }


}
