package com.epam.cinema.spring.controlers;

import com.epam.cinema.spring.enity.*;
import com.epam.cinema.spring.service.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private SeatReservedService seatReservedService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "")
    public String getUserPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails userDetails)) {
            return "redirect:/guest/login";
        }
        User user = userService.findUserByLogin(userDetails.getUsername()).orElseThrow();

        model.addAttribute("tickets", ticketService.findTicketsByUserIDAndCurrentTime(user.getId()));
        model.addAttribute("user", user);
        return Pages.User.USER_PAGE;
    }

    @PostMapping(value = "/update-profile")
    public String updateProfile(@ModelAttribute User user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails userDetails)) {
            return "redirect:/guest/login";
        }
        User userFromDB = userService.findUserByLogin(userDetails.getUsername()).orElseThrow();

        userFromDB.setUserLogin(user.getUserLogin());
        userFromDB.setUserFirstname(user.getUserFirstname());
        userFromDB.setUserSurname(user.getUserSurname());
        userService.updateUser(userFromDB);
        return "redirect:/user";
    }

    @PostMapping(value = "/buy-ticket")
    public String buyTicket(@RequestParam("screeningId") Integer screeningID, @RequestParam("seatId") Integer... seatId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails userDetails)) {
            return "redirect:/guest/login";
        }
        User user = userService.findUserByLogin(userDetails.getUsername()).orElseThrow();

        if (Arrays.stream(seatId).anyMatch(id -> seatReservedService.existsBySeatIdAndScreeningId(id, screeningID))) {
            return "/main";
        }

        Screening screening = screeningService.findScreeningById(screeningID).orElseThrow();
        List<Seat> seats = Arrays.stream(seatId).map(id -> seatService.findSeatById(id).orElseThrow()).toList();

        List<SeatReserved> seatReserved = seats.stream()
                .map(seat -> seatReservedService.addSeatReserved(new SeatReserved(seat, screening)))
                .toList();
        seatReserved.forEach(seat -> ticketService.addTicket(new Ticket(user, seat)));

        return "redirect:/user";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }
}
