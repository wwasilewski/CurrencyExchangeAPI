package pl.sda.CurrencyExchangeAPI.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.CurrencyExchangeAPI.service.StatisticsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatisticsController.class)
public class StatisticsControllerTests {

    @MockBean
    StatisticsService statisticsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHttp4xxWhenInvalidEndpointTest() throws Exception {
        mockMvc.perform(get("/api/stats/invalidEndpoint")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnHttp200ForGetStatisticsForPeriodTest() throws Exception {
        mockMvc.perform(get("/api/stats/EUR/USD/2021-12-12/2021-12-15")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnHttp200ForGetDbRecordsCountTest() throws Exception {
        mockMvc.perform(get("/api/stats/db-all")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnHttp200ForGetDbGoldRecordsCountTest() throws Exception {
        mockMvc.perform(get("/api/stats/db-gold")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnHttp200ForGetCustomCurrencyRecordsCountTest() throws Exception {
        mockMvc.perform(get("/api/stats/currency/pln")).andExpect(status().isOk());
    }
}
