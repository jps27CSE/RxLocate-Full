package com.rko.rxlocate.service;

import com.rko.rxlocate.dto.DivisionDTO;
import com.rko.rxlocate.repository.DivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionService {
    private final DivisionRepository divisionRepository;

    public List<DivisionDTO> getAllDivisions() {
        return divisionRepository.findAll()
                .stream().map(DivisionDTO::from)
                .toList();
    }
}

