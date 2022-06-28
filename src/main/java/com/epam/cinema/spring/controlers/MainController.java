package com.epam.cinema.spring.controlers;

import com.epam.cinema.spring.dto.SeatReservedDto;
import com.epam.cinema.spring.enity.*;
import com.epam.cinema.spring.service.implementation.ScreeningService;
import com.epam.cinema.spring.service.implementation.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class MainController {
    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private SeatService seatService;

    @GetMapping(value = "/main")
    public String getMainPage(Model model, @RequestParam(required = false) LocalDate date,
                              @RequestParam(required = false) String direction, @RequestParam(required = false) String sortBy) {
        if (date == null) {
            date = LocalDate.now();
        }

        if (direction == null) {
            direction = "ask";
        }

        if (sortBy == null) {
            sortBy = "screeningStartTime";
        }

        Map<Movie, List<Screening>> screeningByMovie = screeningService.getGroupedMapScreeningByMovie(date, getDirection(direction), sortBy);
        List<LocalDate> weekLocalDateList = getListOfDate(new Date(System.currentTimeMillis()).getTime(), 7);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("date", date);
        model.addAttribute("direction", direction);
        model.addAttribute("dates", weekLocalDateList);
        model.addAttribute("screenings", screeningByMovie);
        return Pages.MAIN_PAGE;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return Pages.Guest.LOGIN_PAGE;
    }

    @PostMapping(value = "/login")
    public String get() {
        return Pages.MAIN_PAGE;
    }

    @GetMapping(value = "/screening")
    public String getScreeningPage(@RequestParam("screening-id") Integer id, @ModelAttribute("seatReserved") SeatReservedDto seatReserved, Model model) {
        Screening screening = screeningService.findScreeningById(id).orElseThrow();
        List<Seat> seats = seatService.findAllAvailableSeatsByAuditoriumIdAndScreeningId(1, id);
        Map<Integer, List<Seat>> seatsMap = seats.stream().collect(Collectors.groupingBy(Seat::getSeatRow));
        model.addAttribute("seats", seatsMap);
        model.addAttribute("screening", screening);
        return Pages.SCREENING_PAGE;
    }

    private static List<LocalDate> getListOfDate(Long dateInMilSec, Integer numberOfDate) {
        long milsInOneDay = 86_400_000L;
        List<LocalDate> dateList = new LinkedList<>();
        for (int i = 0; i < numberOfDate; i++) {
            LocalDate date = new Date((dateInMilSec + milsInOneDay * i)).toLocalDate();
            dateList.add(date);
        }
        return dateList;
    }

    private Sort.Direction getDirection(String direction) {
        if (direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
