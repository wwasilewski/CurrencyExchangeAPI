package pl.sda.CurrencyExchangeAPI.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ExchangerateReader {


    public String createAPICallForLatestRate(String base, String symbol) {
        return "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + symbol;
    }

    public String createAPICallForHistoryRate(String base, String symbol, LocalDateTime date) {
        return "https://api.exchangerate.host/" + date + "?base=" + base + "&symbols=" + symbol;
    }

    public CurrencyRate getLatestRates(String base, String symbol) {
        URI uri = null;
        try {
            uri = new URI(createAPICallForLatestRate(base, symbol));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
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

        return mapper.fromJson(response.body(), CurrencyRate.class);
    }

    public CurrencyRate getHistoryRates(String base, String symbol, LocalDateTime date) {
        URI uri = null;
        try {
            uri = new URI(createAPICallForHistoryRate(base, symbol, date));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
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

        return mapper.fromJson(response.body(), CurrencyRate.class);
    }
}
