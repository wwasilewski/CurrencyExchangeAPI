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

@WebMvcTest(CurrencyRateController.class)
class CurrencyRateControllerIntegrationTest {

    @MockBean
    CurrencyExchangeService currencyExchangeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void badRequestTest() throws Exception {
        mockMvc.perform(get("/wrongEndpoint")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void okRequestForGoldLatestEndpointTest() throws Exception {
        mockMvc.perform(get("/api/gold/latest")).andExpect(status().isOk());
    }

    @Test
    void okRequestForGoldHistoryEndpoint() throws Exception {
        mockMvc.perform(get("/api/gold/history/2021-12-12")).andExpect(status().isOk());
    }

    @Test
    void okRequestForCurrencyRateLatest() throws Exception {
        mockMvc.perform(get("/api/currency/latest/EUR/USD")).andExpect(status().isOk());
    }

    @Test
    void okRequestForCurrencyRateHistory() throws Exception {
        mockMvc.perform(get("/api/currency/history/USD/EUR/2021-12-12")).andExpect(status().isOk());
    }
}