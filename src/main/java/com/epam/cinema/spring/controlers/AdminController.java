package com.epam.cinema.spring.controlers;

import com.epam.cinema.spring.dto.ScreeningDto;
import com.epam.cinema.spring.enity.Auditorium;
import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.enity.Seat;
import com.epam.cinema.spring.service.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private ScreeningValidationService screeningValidationService;

    @Autowired
    private ImageUploadingService imageUploadingService;

    @GetMapping(value = "")
    public String getAdminPage() {
        return Pages.Admin.ADMIN_PAGE;
    }

    @GetMapping(value = "/add-movie")
    public String getAddMoviePage(@ModelAttribute("movie") Movie movie) {
        return Pages.Admin.ADD_MOVIE_PAGE;
    }

    @GetMapping(value = "/update-schedule")
    public String getSchedulePage(@ModelAttribute("screening") ScreeningDto screeningDto, Model model) {
        model.addAttribute("screenings", screeningService.findScreeningsByDate(LocalDate.now()));
        return Pages.Admin.SCHEDULE_PAGE;
    }

    @GetMapping(value = "/auditorium")
    public String getAuditoriumPage(Model model) {
        Integer auditoriumID = 1;
        Map<Integer, Long> seats = seatService.findAllRowsAndSeats();
        Auditorium auditorium = auditoriumService.findAuditoriumById(auditoriumID).orElseThrow();
        model.addAttribute("seats", seats);
        model.addAttribute("auditorium", auditorium);
        return Pages.Admin.AUDITORIUM_PAGE;
    }

    @GetMapping(value = "/movies")
    public String getMoviePage(HttpServletResponse response, Model model, @CookieValue(name = "numberMovies", defaultValue = "5") String numberMovies,
                               @CookieValue(name = "missedPage", defaultValue = "0") String missedPage) {
        List<Movie> movies = movieService.findMoviesByPagination(PageRequest.of(Integer.parseInt(missedPage), Integer.parseInt(numberMovies)));
        model.addAttribute("movies", movies);
        response.addCookie(new Cookie("numberMovies", numberMovies));
        response.addCookie(new Cookie("missedPage", missedPage));
        response.addCookie(new Cookie("numberAllMovies", String.valueOf(movieService.countAllMovies())));
        return Pages.Admin.MOVIES_PAGE;
    }

    @GetMapping(value = "/update-movie")
    public String getUpdateMoviePage(@RequestParam("movie-id") Integer id, Model model) {
        Movie movie = movieService.findMovieById(id).orElseThrow();
        model.addAttribute("movie", movie);
        return Pages.Admin.UPDATE_MOVIE_PAGE;
    }

    @PostMapping(value = "/update-auditorium")
    public String updateAuditoriumName(@ModelAttribute Auditorium auditorium) {
        auditoriumService.updateAuditorium(auditorium);
        return "redirect:/admin/auditorium";
    }

    @PostMapping(value = "/add-screening")
    public String addScreening(@Valid @ModelAttribute("screening") ScreeningDto screeningDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("screenings", screeningService.findScreeningsByDate(LocalDate.now()));
            return Pages.Admin.SCHEDULE_PAGE;
        }

        if (!movieService.isMovieExist(screeningDto.getMovieName())) {
            model.addAttribute("screenings", screeningService.findScreeningsByDate(LocalDate.now()));
            ObjectError error = new ObjectError("global", "screening.movieNotExist");
            result.addError(error);
            return Pages.Admin.SCHEDULE_PAGE;
        }

        Screening screening = screeningDto.getScreeningFromDto();
        screening.setAuditorium(auditoriumService.findAuditoriumById(1).orElseThrow());
        screening.setMovie(movieService.findMovieByName(screeningDto.getMovieName()).orElseThrow());

        String err = screeningValidationService.validateScreening(screening);
        if (!err.isBlank()) {
            ObjectError error = new ObjectError("global", err);
            result.addError(error);
            return Pages.Admin.SCHEDULE_PAGE;
        }

        screeningService.addScreening(screening);
        return "redirect:/admin/update-schedule";
    }

    @PostMapping(value = "/delete-screening")
    public String deleteScreening(@RequestParam("id") Integer id, Model model) {
        if (screeningService.isScreeningHaveTickets(id)) {
            model.addAttribute("error", "screening.haveTicket");
            return Pages.Admin.ADMIN_PAGE;
        }

        Screening screening = new Screening();
        screening.setId(id);
        screeningService.deleteScreening(screening);
        return "redirect:/admin/update-schedule";
    }

    @PostMapping(value = "/delete-movie")
    public String deleteMovie(@RequestParam("movie-id") Integer id, Model model) {
        if (screeningService.isScreeningExistByMovieId(id)) {
            model.addAttribute("error", "movie.delete.error");
            return Pages.Admin.ADMIN_PAGE;
        }

        Movie movie = new Movie();
        movie.setId(id);
        movieService.deleteMovie(movie);
        return "redirect:/admin/movies";
    }

    @PostMapping(value = "/add-movie")
    public String addMovie(@RequestParam("image") MultipartFile multipartFile, @Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return Pages.Admin.ADD_MOVIE_PAGE;
        }

        if (!multipartFile.isEmpty()) {
            movie.setMovieImgUrl(imageUploadingService.save(multipartFile));
        }

        movieService.addMovie(movie);
        return "redirect:/admin/add-movie";
    }

    @PostMapping(value = "/update-movie")
    public String updateMovie(@ModelAttribute Movie movie, @RequestParam("image") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            movie.setMovieImgUrl(imageUploadingService.save(multipartFile));
        }

        movieService.updateMovie(movie);
        return "redirect:/admin/movies";
    }

    @PostMapping(value = "/update-seats")
    public String updateSeats(HttpServletRequest request) {
        Auditorium auditorium = auditoriumService.findAuditoriumById(1).orElseThrow();
        List<Seat> seatList = new LinkedList<>();
        String[] array = request.getParameterMap().get("seat-number");

        for (int row = 1; row <= array.length; row++) {
            int seatNumber = Integer.parseInt(array[row - 1]);
            for (int number = 1; number <= seatNumber; number++) {
                seatList.add(new Seat(auditorium, row, number));
            }
        }

        seatService.updateSeats(seatList);
        return "redirect:/admin/auditorium";
    }
}
