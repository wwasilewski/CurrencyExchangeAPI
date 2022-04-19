package pl.sda.CurrencyExchangeAPI.service;

import org.springframework.stereotype.Component;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;

@Component
public class CurrencyMapper {

    public CurrencyRateDto map(CurrencyRate currencyRate) {

        return new CurrencyRateDto(currencyRate.getRate(), currencyRate.getDate());
    }
}
