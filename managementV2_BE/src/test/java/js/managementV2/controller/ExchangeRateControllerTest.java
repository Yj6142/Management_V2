package js.managementV2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExchangeRateController.class)
class ExchangeRateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void exchangeAPI_호출() throws Exception {

        mvc.perform(get("/todayRate"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}