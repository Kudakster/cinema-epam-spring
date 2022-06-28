package com.epam.cinema.spring.controler;

import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.service.implementation.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScreeningService screeningService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void givenMainController_whenOpenMainPageWithDateThatHaveScreening_thenReturnNotEmptyMap() throws Exception {
        Movie movie = Movie.builder()
                .movieName("Maven")
                .movieImgUrl("url")
                .build();
        Screening screening = Screening.builder()
                .movie(movie)
                .screeningStartTime(LocalTime.MIN)
                .build();
        LocalDate date = LocalDate.now();
        Map<Movie, List<Screening>> movieListMap = new LinkedHashMap<>();
        movieListMap.put(movie, List.of(screening));

        given(screeningService.getGroupedMapScreeningByMovie(date, Sort.Direction.ASC, "screeningStartTime"))
                .willReturn(movieListMap);

        mvc.perform(get("/main")
                        .param("date", String.valueOf(date))
                        .contentType("application/html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Maven")));
    }

    @Test
    public void givenMainController_whenOpenLoginPage_thenReturnString() throws Exception {
        mvc.perform(get("/login")
                        .contentType("application/html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign in")));
    }
}
