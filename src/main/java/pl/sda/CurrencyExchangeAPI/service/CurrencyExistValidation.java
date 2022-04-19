package pl.sda.CurrencyExchangeAPI.service;

import org.springframework.stereotype.Component;

@Component
public class CurrencyExistValidation {

    private final ExchangerateReader exchangerateReader;

    public CurrencyExistValidation(ExchangerateReader exchangerateReader) {
        this.exchangerateReader = exchangerateReader;
    }

    public boolean isCurrencyExisting(String currency) {
        return exchangerateReader.getAllCurrencyMap()
                .getRates()
                .containsKey(currency);
    }
}
