package com.rko.rxlocate.controller;

import com.rko.rxlocate.dto.PrescriptionDTO;
import com.rko.rxlocate.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionDetails(@PathVariable Long id) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionDetailsByRxNumber(id), HttpStatus.OK);
    }
}