package com.rko.rxlocate.service;

import com.rko.rxlocate.dto.AreaDTO;
import com.rko.rxlocate.dto.DistrictDTO;
import com.rko.rxlocate.dto.DivisionDTO;
import com.rko.rxlocate.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final PrescriptionRepository prescriptionRepository;

    public List<DivisionDTO> getDivisionsByDrugName(String drugName) {
        Pageable pageable = PageRequest.of(0, 10);
        return prescriptionRepository.findDivisionsByDrugName(drugName, pageable);
    }

    public List<DivisionDTO> getDivisionsByDrugId(Long drugId) {
        Pageable pageable = PageRequest.of(0, 10);
        return prescriptionRepository.findDivisionsByDrugId(drugId, pageable);
    }

    public List<DistrictDTO> getDistrictsByDivisionAndDrugName(String drugName, String divisionName) {
        Pageable pageable = PageRequest.of(0, 10);
        return prescriptionRepository.findDistrictsByDivisionAndDrugName(drugName, divisionName, pageable);
    }

    public List<DistrictDTO> getDistrictsByDivisionAndDrugId(Long drugId, Long divisionId) {
        Pageable pageable = PageRequest.of(0, 10);
        return prescriptionRepository.findDistrictsByDivisionAndDrugId(drugId, divisionId, pageable);
    }

    public List<AreaDTO> getAreasByDivisionAndDistrictAndDrugName(String drugName, String divisionName, String districtName) {
        Pageable pageable = PageRequest.of(0, 10);
        return prescriptionRepository.findAreasByDivisionAndDistrictAndDrugName(drugName, divisionName, districtName, pageable);
    }

    public List<AreaDTO> getAreasByDivisionAndDistrictAndDrugId(Long drugId, Long divisionId, Long districtId) {
        Pageable pageable = PageRequest.of(0, 10);
        return prescriptionRepository.findAreasByDivisionAndDistrictAndDrugId(drugId, divisionId, districtId, pageable);
    }

    public List<?> mapSummary(String drugName, String divisionName) {
        Pageable pageable = PageRequest.of(0, 10);
        if (Objects.isNull(divisionName)) {
            return prescriptionRepository.findDivisionsByDrugName(drugName, pageable);
        } else {
            return prescriptionRepository.findDistrictsByDivisionAndDrugName(drugName, divisionName, pageable);
        }
    }

}
