package pl.sda.CurrencyExchangeAPI.service;

public class ExchangeProcessingException extends RuntimeException {

    public ExchangeProcessingException(final String message) {
        super(message);
    }
}
