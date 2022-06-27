package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.repository.ScreeningRepository;
import com.epam.cinema.spring.service.IScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Screening> findScreeningsByDateAndTime(LocalDate date, LocalTime time) {
        return screeningRepository.findByScreeningDateIsGreaterThanEqualAndScreeningStartTimeIsGreaterThanEqualOrderByScreeningStartTimeAsc(date, time);
    }

    public Map<Movie, List<Screening>> getGroupedMapScreeningByMovie(LocalDate date) {
        Map<Movie, List<Screening>> map = new LinkedHashMap<>();
        LocalTime time = LocalTime.MIDNIGHT;
        if (date.isEqual(LocalDate.now())) {
            time = LocalTime.now();
        }

        List<Screening> screeningList = findScreeningsByDateAndTime(date, time);

        if (screeningList != null) {
            map = screeningList.stream()
                    .collect(Collectors.groupingBy(Screening::getMovie));
            map = sortByValue(map);
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
    public void deleteScreening(Screening screening) {
        screeningRepository.delete(screening);
    }

    private static Map<Movie, List<Screening>> sortByValue(Map<Movie, List<Screening>> map) {
        List<Map.Entry<Movie, List<Screening>>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue((o1, o2) -> {
            if (o1.get(0).getScreeningStartTime().getLong(ChronoField.MINUTE_OF_DAY) > o2.get(0).getScreeningEndTime().getLong(ChronoField.MINUTE_OF_DAY)) {
                return 1;
            } else if (o1.get(0).getScreeningStartTime().getLong(ChronoField.MINUTE_OF_DAY) < o2.get(0).getScreeningStartTime().getLong(ChronoField.MINUTE_OF_DAY)) {
                return -1;
            }
            return 0;
        }));
        Map<Movie, List<Screening>> finalMap = new LinkedHashMap<>();
        list.forEach(e -> finalMap.put(e.getKey(), e.getValue()));
        return finalMap;
    }
}
