package pl.sda.CurrencyExchangeAPI.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.CurrencyExchangeAPI.model.RateValue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class ExchangerateReader {


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

    public RateValue getLatestRates(String base, String symbol) {
        URI uri = null;
        try {
            uri = new URI(createAPICallForLatestRate(base, symbol));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return getRateValue(uri);
    }


    public RateValue getHistoryRates(String base, String symbol, String date) {
        URI uri = null;
        try {
            uri = new URI(createAPICallForHistoryRate(base, symbol, date));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return getRateValue(uri);
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
        Gson mapper = new Gson();

        return mapper.fromJson(response.body(), RateValue.class);
    }
}
