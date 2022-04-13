package pl.sda.CurrencyExchangeAPI.service;

import org.springframework.stereotype.Service;
import pl.sda.CurrencyExchangeAPI.dto.CurrencyRateDto;

import java.time.LocalDateTime;

@Service
public class CurrencyExchangeService {

    //TODO
    public CurrencyRateDto getLatestCurrencyRate(String base, String target) {
        return null;
    }

    //TODO
    public CurrencyRateDto getOldCurrencyRate(String base, String target, LocalDateTime date) {
        return null;
    }
}
