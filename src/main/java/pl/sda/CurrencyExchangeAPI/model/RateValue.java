package pl.sda.CurrencyExchangeAPI.model;

import lombok.Data;

import java.util.Map;

@Data
public class RateValue {
    private Map<String, Double> rates;
}
