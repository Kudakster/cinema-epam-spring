package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Auditorium;
import com.epam.cinema.spring.repository.AuditoriumRepository;
import com.epam.cinema.spring.service.implementation.AuditoriumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditoriumServiceTest {
    @Mock
    private AuditoriumRepository auditoriumRepository;

    @InjectMocks
    private AuditoriumService auditoriumService;

    private Auditorium auditorium;

    @BeforeEach
    public void setup() {
        auditorium = Auditorium.builder()
                .id(1)
                .auditoriumName("First")
                .build();
    }

    @DisplayName("JUnit test for findAuditorium method")
    @Test
    public void givenAuditoriumObject_whenFindAuditoriumByID_thenReturnAuditoriumObject() {
        given(auditoriumRepository.findById(auditorium.getId()))
                .willReturn(Optional.of(auditorium));

        Optional<Auditorium> findAuditorium = auditoriumService.findAuditoriumById(auditorium.getId());
        assertThat(findAuditorium).isPresent();
        assertThat(findAuditorium.get()).isInstanceOf(Auditorium.class);
    }

    @DisplayName("JUnit test for findAuditorium method")
    @Test
    public void givenAuditoriumObject_whenFindAuditoriumByName_thenReturnAuditoriumObject() {
        given(auditoriumRepository.findByAuditoriumName(auditorium.getAuditoriumName()))
                .willReturn(Optional.of(auditorium));

        Optional<Auditorium> findAuditorium = auditoriumService.findAuditoriumByName(auditorium.getAuditoriumName());
        assertThat(findAuditorium).isPresent();
        assertThat(findAuditorium.get()).isInstanceOf(Auditorium.class);
    }

    @DisplayName("JUnit test for findAllAuditorium method")
    @Test
    public void givenAuditoriumObject_whenFindAllAuditorium_thenReturnListOfAuditoriumObject() {
        Auditorium anotherAuditorium = Auditorium.builder()
                .id(2)
                .auditoriumName("Second")
                .build();

        given(auditoriumRepository.findAll()).willReturn(List.of(auditorium, anotherAuditorium));

        List<Auditorium> auditoriums = auditoriumService.findAllAuditoriums();
        assertThat(auditoriums).isNotNull();
        assertThat(auditoriums.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveAuditorium method")
    @Test
    public void givenAuditoriumObject_whenAddAuditorium_thenReturnAuditoriumObject() {
        given(auditoriumRepository.save(auditorium)).willReturn(auditorium);

        Auditorium savedAuditorium = auditoriumService.addAuditorium(auditorium);
        assertThat(savedAuditorium).isNotNull();
        assertThat(savedAuditorium).isInstanceOf(Auditorium.class);
    }

    @DisplayName("JUnit test for updateAuditorium method")
    @Test
    public void givenAuditoriumObject_whenUpdateAuditorium_thenReturnUpdateAuditoriumObject() {
        auditorium.setAuditoriumName("Next Generation");

        given(auditoriumRepository.save(auditorium)).willReturn(auditorium);

        Auditorium updateAuditorium = auditoriumService.updateAuditorium(auditorium);
        assertThat(updateAuditorium).isNotNull();
        assertThat(updateAuditorium).isInstanceOf(Auditorium.class);
        assertThat(updateAuditorium.getAuditoriumName()).isEqualTo("Next Generation");
    }

    @DisplayName("JUnit test for deleteAuditorium method")
    @Test
    public void givenAuditoriumObject_whenDeleteAuditorium_thenVoid() {
        willDoNothing().given(auditoriumRepository).delete(auditorium);
        auditoriumService.deleteAuditorium(auditorium);
        verify(auditoriumRepository, times(1)).delete(auditorium);
    }
}
