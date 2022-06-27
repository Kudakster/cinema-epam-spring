package com.epam.cinema.spring.controler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class GuestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenGuest_whenGetRegistration_thenReturnString() throws Exception {
       this.mvc.perform(get("/guest/registration")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration form")));
    }
}
