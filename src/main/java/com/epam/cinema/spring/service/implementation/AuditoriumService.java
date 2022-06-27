package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Auditorium;
import com.epam.cinema.spring.repository.AuditoriumRepository;
import com.epam.cinema.spring.service.IAuditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoriumService implements IAuditorium {
    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public Optional<Auditorium> findAuditoriumById(Integer id) {
        return auditoriumRepository.findById(id);
    }

    @Override
    public Optional<Auditorium> findAuditoriumByName(String name) {
        return auditoriumRepository.findByAuditoriumName(name);
    }

    @Override
    public List<Auditorium> findAllAuditoriums() {
        return auditoriumRepository.findAll();
    }

    @Override
    public Auditorium addAuditorium(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }

    @Override
    public Auditorium updateAuditorium(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }

    @Override
    public void deleteAuditorium(Auditorium auditorium) {
        auditoriumRepository.delete(auditorium);
    }
}
