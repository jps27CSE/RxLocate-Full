package com.rko.rxlocate.service;

import com.rko.rxlocate.dto.DistrictPrescriptionProjection;
import com.rko.rxlocate.dto.DivisionPrescriptionProjection;
import com.rko.rxlocate.helper.ExcelHelper;
import com.rko.rxlocate.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final PrescriptionRepository prescriptionRepository;

    public ByteArrayInputStream getExcelDataForDivision(String drugName) {
        List<DivisionPrescriptionProjection> divisionPrescriptionProjections = prescriptionRepository.excelDataByDrugName(drugName);
        return ExcelHelper.divisionDataToExcel(divisionPrescriptionProjections);
    }

    public ByteArrayInputStream getExcelDataForDivision(Long drugId) {
        List<DivisionPrescriptionProjection> divisionPrescriptionProjections = prescriptionRepository.excelDataByDrugId(drugId);
        return ExcelHelper.divisionDataToExcel(divisionPrescriptionProjections);
    }

    public ByteArrayInputStream getExcelDataForDistrict(String drugName, String  divisionName) {
        List<DistrictPrescriptionProjection> districtPrescriptionProjections = prescriptionRepository.excelDataByDrugNameAndDivisionName(drugName, divisionName);
        return ExcelHelper.districtDataToExcel(districtPrescriptionProjections);
    }

    public ByteArrayInputStream getExcelDataForDistrict(Long drugId, Long divisionId) {
        List<DistrictPrescriptionProjection> districtPrescriptionProjections = prescriptionRepository.excelDataByDrugNameAndDivisionName(drugId, divisionId);
        return ExcelHelper.districtDataToExcel(districtPrescriptionProjections);
    }


}