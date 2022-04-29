package pl.sda.CurrencyExchangeAPI.model;

import lombok.Data;

import java.util.Map;

@Data
public class StatisticsValue {

    private String base;
    private String start_date;
    private String end_date;
    private Map<String, Map<String, Double>> rates;
}
