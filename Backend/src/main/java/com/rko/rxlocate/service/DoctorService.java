package com.rko.rxlocate.service;

import com.rko.rxlocate.domain.Doctor;
import com.rko.rxlocate.dto.DoctorDTO;
import com.rko.rxlocate.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorDTO findByBmdcId(String bmdcId) {
        Optional<Doctor> doctor = doctorRepository.findByBmdcId(bmdcId);
        return doctor.map(DoctorDTO::from).orElse(null);
    }
}
