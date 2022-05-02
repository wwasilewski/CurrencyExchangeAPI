package pl.sda.CurrencyExchangeAPI.model;

public enum Currency {

    GOLD("XAU"),
    PLN("PLN");

    public final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }
}
