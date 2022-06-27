package com.epam.cinema.spring.controler;

import com.epam.cinema.spring.controlers.MainController;
import com.epam.cinema.spring.service.implementation.ScreeningService;
import com.epam.cinema.spring.service.implementation.SeatService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MockMvc mvc;

    @Mock
    private ScreeningService screeningService;

    @Mock
    private SeatService seatService;

    @Test
    public void givenMainController_whenMainPage_thenReturnString() throws Exception {
        this.mvc.perform(get("/main")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration form")));
    }

}
