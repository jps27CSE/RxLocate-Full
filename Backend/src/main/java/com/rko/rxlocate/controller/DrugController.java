package com.rko.rxlocate.controller;

import com.rko.rxlocate.dto.DrugDTO;
import com.rko.rxlocate.dto.DrugDetailsDTO;
import com.rko.rxlocate.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drug")
public class DrugController {
    private final DrugService drugService;

    @GetMapping("/list")
    public ResponseEntity<List<DrugDTO>> getAllDrugs() {
        List<DrugDTO> drugs = drugService.getAllDrugs();
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<?> getAllDrugs(@PathVariable String name) {
        List<DrugDTO> drugs = drugService.getAllDrugs(name);
        return new ResponseEntity<>(drugs, HttpStatus.OK);
    }

    @GetMapping("/info/{drugName}")
    public ResponseEntity<DrugDetailsDTO> getDrugByName(@PathVariable String drugName) {
        DrugDetailsDTO drug = drugService.fetchDrugInfoByName(drugName);
        return ResponseEntity.ok(drug);
    }
}