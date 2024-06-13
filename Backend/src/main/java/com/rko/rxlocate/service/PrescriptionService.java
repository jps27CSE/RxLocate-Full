package com.rko.rxlocate.service;

import com.rko.rxlocate.domain.Prescription;
import com.rko.rxlocate.dto.PrescriptionDTO;
import com.rko.rxlocate.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public PrescriptionDTO getPrescriptionDetailsByRxNumber(Long prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);
        return prescription.map(PrescriptionDTO::from).orElse(null);
    }

}
