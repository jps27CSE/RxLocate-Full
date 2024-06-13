package com.rko.rxlocate.service;

import com.rko.rxlocate.domain.Drug;
import com.rko.rxlocate.dto.DrugDTO;
import com.rko.rxlocate.dto.DrugDetailsDTO;
import com.rko.rxlocate.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugService {
private final DrugRepository drugRepository;

    public List<DrugDTO> getAllDrugs(String name) {
        Pageable pageable = PageRequest.of(0, 10);
        return drugRepository.findByNameContaining(name, pageable);
    }

    public DrugDetailsDTO fetchDrugInfoByName(String name) {
        return drugRepository.fetchDrugInfoByName(name);
    }


    public List<DrugDTO> getAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();
        return drugs.stream()
                .map(DrugDTO::from)
                .collect(Collectors.toList());
    }
}
