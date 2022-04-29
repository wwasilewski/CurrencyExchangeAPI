package pl.sda.CurrencyExchangeAPI.model;

import lombok.Data;

import java.util.Map;

@Data
public class StatisticsValue {

    private String base;
    private Map<String, Map<String, Double>> rates;
}
