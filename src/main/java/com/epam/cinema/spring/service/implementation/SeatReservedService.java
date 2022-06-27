package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.SeatReserved;
import com.epam.cinema.spring.service.ISeatReservedService;
import com.epam.cinema.spring.repository.SeatReservedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatReservedService implements ISeatReservedService {
    @Autowired
    private SeatReservedRepository seatReservedRepository;

    @Override
    public Optional<SeatReserved> findSeatReservedById(Integer id) {
        return seatReservedRepository.findById(id);
    }

    @Override
    public Optional<SeatReserved> findSeatReservedBySeatIdAndScreeningId(Integer seatId, Integer screeningId) {
        return seatReservedRepository.findBySeat_IdAndScreening_Id(seatId, screeningId);
    }

    @Override
    public List<SeatReserved> findAllSeatReserved() {
        return seatReservedRepository.findAll();
    }

    @Override
    public List<SeatReserved> findAllSeatReservedByScreeningId(Integer id) {
        return seatReservedRepository.findByScreening_Id(id);
    }

    @Override
    public SeatReserved addSeatReserved(SeatReserved seatReserved) {
        return seatReservedRepository.save(seatReserved);
    }

    @Override
    public void deleteSeatReserved(SeatReserved seatReserved) {
        seatReservedRepository.delete(seatReserved);
    }

    @Override
    public boolean existsBySeatIdAndScreeningId(Integer seatId, Integer screeningId) {
        return seatReservedRepository.existsBySeat_IdAndScreening_Id(seatId, screeningId);
    }
}
