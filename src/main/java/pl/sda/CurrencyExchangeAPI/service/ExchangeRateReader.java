package pl.sda.CurrencyExchangeAPI.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.CurrencyExchangeAPI.model.RateValue;
import pl.sda.CurrencyExchangeAPI.model.StatisticsValue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class ExchangeRateReader {

    private final Gson gson;

    @Autowired
    public ExchangeRateReader(Gson gson) {
        this.gson = gson;
    }

    public String createAPICallForLatestRate(String base, String symbol) {
        return "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + symbol;
    }

    //date format YYYY-MM-DD
    public String createAPICallForHistoryRate(String base, String symbol, String date) {
        return "https://api.exchangerate.host/" + date + "?base=" + base + "&symbols=" + symbol;
    }

    public String createAPICallForLatestGoldRate() {
        return "https://api.exchangerate.host/latest?base=XAU&symbols=PLN";
    }

    //date format YYYY-MM-DD
    public String createAPICallForHistoryGoldRate(String date) {
        return "https://api.exchangerate.host/" + date + "?base=XAU&symbols=PLN";
    }

    public String createAPICallForAllCurrencyMap() {
        return "https://api.exchangerate.host/latest";
    }

    public String createAPICallForStatisticsForPeriod(String base, String target, String dateFrom, String dateTo) {
        return "https://api.exchangerate.host/timeseries?start_date=" + dateFrom + "&end_date=" + dateTo + "&base=" + base + "&symbols=" + target.toUpperCase();
    }

    public RateValue getLatestRates(String base, String symbol) {
        URI uri = null;
        if (isCurrencyExisting(base, symbol)) {
            try {
                uri = new URI(createAPICallForLatestRate(base, symbol));
            } catch (URISyntaxException e) {
                log.error(e.getMessage(), e);
            }
            return getRateValue(uri);
        } else {
            throw new ExchangeProcessingException("No such currency found");
        }
    }

    public RateValue getHistoryRates(String base, String symbol, String date) {
        URI uri = null;
        if (isCurrencyExisting(base, symbol)) {
            try {
                uri = new URI(createAPICallForHistoryRate(base, symbol, date));
            } catch (URISyntaxException e) {
                log.error(e.getMessage(), e);
            }
            return getRateValue(uri);
        } else {
            throw new ExchangeProcessingException("No such currency found");
        }
    }

    public StatisticsValue getStatisticsForPeriod(String base, String target, String dateFrom, String dateTo) {
        URI uri = null;
        if (isCurrencyExisting(base, target)) {
            try {
                uri = new URI(createAPICallForStatisticsForPeriod(base, target, dateFrom, dateTo));
            } catch (URISyntaxException e) {
                log.error(e.getMessage(), e);
            }
            return getStatsValue(uri);
        } else {
            throw new ExchangeProcessingException("No such currency found");
        }
    }

    public RateValue getLatestGoldRates() {
        URI uri = null;
        try {
            uri = new URI(createAPICallForLatestGoldRate());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return getRateValue(uri);
    }

    public RateValue getHistoryGoldRates(String date) {
        URI uri = null;
        try {
            uri = new URI(createAPICallForHistoryGoldRate(date));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return getRateValue(uri);
    }

    public RateValue getAllCurrencyMap() {
        URI uri = null;
        try {
            uri = new URI(createAPICallForAllCurrencyMap());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return getRateValue(uri);
    }

    private RateValue getRateValue(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return gson.fromJson(response.body(), RateValue.class);
    }

    private StatisticsValue getStatsValue(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return gson.fromJson(response.body(), StatisticsValue.class);
    }

    public boolean isCurrencyExisting(String base, String target) {
        return getAllCurrencyMap()
                .getRates()
                .containsKey(base.toUpperCase())
                && getAllCurrencyMap()
                .getRates()
                .containsKey(target.toUpperCase());
    }
}
