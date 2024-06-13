package com.rko.rxlocate.controller;

import com.rko.rxlocate.dto.DoctorDTO;
import com.rko.rxlocate.dto.DoctorProjection;
import com.rko.rxlocate.repository.DoctorRepository;
import com.rko.rxlocate.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    private final DoctorRepository doctorRepository;

    @GetMapping("/info/{bmdcId}")
    public ResponseEntity<?> getDoctorByBmdcId(@PathVariable String bmdcId) {
        DoctorDTO doctor = doctorService.findByBmdcId(bmdcId);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/{drugName}")
    public ResponseEntity<?> getDoctorsByDrug(@PathVariable String drugName) {
        List<DoctorProjection> doctors = doctorRepository.findDoctorsWithDivisionByDrug(drugName);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/by/{drugId}")
    public ResponseEntity<?> getDoctorsByDrugId(@PathVariable Long drugId) {
        List<DoctorProjection> doctors = doctorRepository.findDoctorsWithDivisionByDrugId(drugId);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{divisionName}/{drugName}")
    public ResponseEntity<?> getDoctorsByDivisionAndDrug(@PathVariable String  divisionName,
                                                         @PathVariable String  drugName) {
        List<DoctorProjection> doctorDTOS = doctorRepository.findDoctorsWithDivisionNameByDrugName(divisionName, drugName);
        return ResponseEntity.ok(doctorDTOS);
    }

    @GetMapping("/by/{divisionId}/{drugId}")
    public ResponseEntity<?> getDoctorsByDivisionAndDrug(@PathVariable Long divisionId,
                                                         @PathVariable Long drugId) {
        List<DoctorProjection> doctorDTOS = doctorRepository.findDoctorsWithDivisionIdByDrugId(divisionId, drugId);
        return ResponseEntity.ok(doctorDTOS);
    }


}
