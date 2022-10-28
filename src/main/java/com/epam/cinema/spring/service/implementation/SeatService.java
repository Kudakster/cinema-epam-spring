package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Seat;
import com.epam.cinema.spring.enity.SeatReserved;
import com.epam.cinema.spring.repository.SeatRepository;
import com.epam.cinema.spring.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SeatService implements ISeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatReservedService seatReservedService;

    @Override
    public Optional<Seat> findSeatById(Integer id) {
        return seatRepository.findById(id);
    }

    @Override
    public List<Seat> findAllSeats(Integer id) {
        return seatRepository.findByAuditorium_Id(id);
    }

    @Override
    public List<Seat> findAllSeatsByAuditoriumId(Integer id) {
        return seatRepository.findByAuditorium_IdOrderBySeatRowAscSeatNumberAsc(id);
    }

    @Override
    public Map<Integer, Map<Seat, Boolean>> findAllAvailableSeatsByAuditoriumIdAndScreeningId(Integer auditoriumId, Integer screeningId) {
        List<Seat> seatReserved = seatReservedService.findAllSeatReservedByScreeningId(screeningId)
                .stream()
                .map(SeatReserved::getSeat)
                .toList();

        List<Seat> seats = findAllSeatsByAuditoriumId(auditoriumId);
        Map<Integer, List<Seat>> integerListMap = seats.stream().collect(Collectors.groupingBy(Seat::getSeatRow));
        Map<Integer, Map<Seat, Boolean>> map = new HashMap<>();
        integerListMap.forEach((key, list) -> {
            Map<Seat, Boolean> seatBooleanMap = new TreeMap<>((o1, o2) -> {
                if (o1.getSeatNumber().equals(o2.getSeatNumber())) {
                    return 0;
                } else {
                    return o1.getSeatNumber() < o2.getSeatNumber() ? -1 : 1;
                }
            });
            list.forEach(seat -> seatBooleanMap.put(seat, !seatReserved.contains(seat)));
            map.put(key, seatBooleanMap);
        });
        return map;
    }

    @Override
    public Map<Integer, Long> findAllRowsAndSeatsByAuditoriumId(Integer auditoriumId) {
        List<Seat> seats = findAllSeats(auditoriumId);

        if (seats != null && seats.size() != 0) {
            return seats.stream()
                    .map(Seat::getSeatRow)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }

        return null;
    }

    @Override
    public Seat addSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Seat seat) {
        seatRepository.delete(seat);
    }

    @Override
    public List<Seat> updateSeats(List<Seat> seatList) {
        if (seatList == null) {
            return null;
        }

        deleteSeats(seatList);
        return addSeats(seatList);
    }

    @Override
    public List<Seat> addSeats(List<Seat> seatList) {
        seatList.removeIf(seat -> seatRepository.findBySeatRowAndSeatNumber(seat.getSeatRow(), seat.getSeatNumber()).isPresent());
        if (seatList.size() != 0) {
            return seatRepository.saveAll(seatList);
        }
        return null;
    }

    @Override
    public void deleteSeats(List<Seat> seatList) {
        int auditoriumId = 1;
        Set<Integer> setRowAndNumber = seatList.stream()
                .map(seat -> Integer.parseInt(seat.getSeatRow() + "" + seat.getSeatNumber()))
                .collect(Collectors.toSet());
        List<Seat> deleteSeatsList = findAllSeats(auditoriumId);

        if (deleteSeatsList != null) {
            setRowAndNumber.forEach(System.out::println);
            deleteSeatsList.removeIf(seat -> setRowAndNumber.stream()
                    .anyMatch(num -> num == Integer.parseInt(seat.getSeatRow() + "" + seat.getSeatNumber())));
            if (deleteSeatsList.size() > 0) {
                seatRepository.deleteAll(deleteSeatsList);
            }
        }
    }
}
