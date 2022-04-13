package pl.sda.CurrencyExchangeAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CurrencyRate {
    private int id;
    private String base;
    private String target;
    private double rate;
    private LocalDateTime date;
}
