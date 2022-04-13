package pl.sda.CurrencyExchangeAPI.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class ExchangerateReader {



    public String createAPICall(String base, String symbol) {
    return "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + symbol;
    }

    public CurrencyRate getLatestRates(String base, String symbol) {
        URI uri = null;
        try {
            uri = new URI(createAPICall(base, symbol));
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
