package com.example.kino_aplikacija.service;


import com.example.kino_aplikacija.model.Screening;
import com.example.kino_aplikacija.repository.ScreeningRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Transactional
    public Screening saveScreening(Screening screening) {
        return screeningRepository.save(screening);
    }

    public Optional<Screening> getScreeningbyId(Long id) {
        return screeningRepository.findById(id);
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }
    public void deleteScreeningById(Long id){
        screeningRepository.deleteById (id);
    }
}
