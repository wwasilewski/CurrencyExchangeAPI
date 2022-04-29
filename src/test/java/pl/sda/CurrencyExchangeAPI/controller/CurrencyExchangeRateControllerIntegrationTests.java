package pl.sda.CurrencyExchangeAPI.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.CurrencyExchangeAPI.service.CurrencyExchangeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyExchangeRateController.class)
public class CurrencyExchangeRateControllerIntegrationTests {

    @MockBean
    CurrencyExchangeService currencyExchangeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHttp4xxWhenInvalidEndpointTest() throws Exception {
        mockMvc.perform(get("/api/currency/invalidEndpoint")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnHttp200ForGetLatestCurrencyRateTest() throws Exception {
        mockMvc.perform(get("/api/currency/latest/EUR/USD")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnHttp200ForGetOldCurrencyRateTest() throws Exception {
        mockMvc.perform(get("/api/currency/history/USD/EUR/2021-12-12")).andExpect(status().isOk());
    }
}
