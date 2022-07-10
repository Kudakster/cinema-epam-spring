package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.enity.SeatReserved;
import com.epam.cinema.spring.repository.ScreeningRepository;
import com.epam.cinema.spring.repository.SeatReservedRepository;
import com.epam.cinema.spring.repository.TicketRepository;
import com.epam.cinema.spring.service.IScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScreeningService implements IScreeningService {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatReservedRepository seatReservedRepository;

    @Override
    public Optional<Screening> findScreeningById(Integer id) {
        return screeningRepository.findById(id);
    }

    @Override
    public Optional<Screening> findScreeningByDateAndAuditoriumID(LocalDate date, Integer id) {
        return screeningRepository.findByScreeningDateAndAuditorium_Id(date, id);
    }

    @Override
    public List<Screening> findAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public List<Screening> findScreeningsByDate(LocalDate date) {
        return screeningRepository.findByScreeningDateGreaterThanEqualOrderByScreeningDateAscScreeningStartTimeAsc(date);
    }

    @Override
    public List<Screening> findScreeningsByDateAndTime(LocalDate date, LocalTime time, Sort.Direction direction, String sortBy) {
        return screeningRepository.findByScreeningDateIsGreaterThanEqualAndScreeningStartTimeIsGreaterThanEqual(date, time, Sort.by(direction, sortBy));
    }

    public Map<Movie, List<Screening>> getGroupedMapScreeningByMovie(LocalDate date, Sort.Direction direction, String sortBy) {
        Map<Movie, List<Screening>> map = new LinkedHashMap<>();
        List<Screening> screeningList;
        LocalTime time = LocalTime.MIDNIGHT;
        if (date.isEqual(LocalDate.now())) {
            time = LocalTime.now();
        }

        if (sortBy.equals("seatAvailable")) {
            screeningList = findScreeningsByDateAndTime(date, time, Sort.Direction.ASC, "screeningStartTime");
            screeningList = sortBySeatReserved(screeningList, direction);
        } else {
            screeningList = findScreeningsByDateAndTime(date, time, direction, sortBy);
        }

        if (screeningList != null) {
            map = screeningList.stream()
                    .collect(Collectors.groupingByConcurrent(Screening::getMovie));
        }
        return map;
    }

    @Override
    public Screening addScreening(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public Screening updateScreening(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public boolean isScreeningExistByMovieId(Integer id) {
        return screeningRepository.existsByMovie_Id(id);
    }

    @Override
    public boolean isScreeningHaveTickets(Integer id) {
        return ticketRepository.existsBySeatReserved_Screening_Id(id);
    }

    @Override
    public void deleteScreening(Screening screening) {
        screeningRepository.delete(screening);
    }

    private List<Screening> sortBySeatReserved(List<Screening> list, Sort.Direction direction) {
        Map<Screening, Integer> seatAvailableMap = new LinkedHashMap<>();
        list.forEach(screening -> seatAvailableMap.put(screening, Math.toIntExact(seatReservedRepository.countByScreening_Id(screening.getId()))));

        if (direction == Sort.Direction.DESC) {
            return seatAvailableMap.entrySet()
                    .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }

        return seatAvailableMap.entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
