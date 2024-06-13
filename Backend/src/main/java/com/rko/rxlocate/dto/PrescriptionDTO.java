package com.rko.rxlocate.dto;

import com.rko.rxlocate.domain.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {
    private Long id;

    private DoctorDTO doctor;

    private PatientDTO patient;

    private List<DrugDTO> drugs;

    public static PrescriptionDTO from(Prescription prescription) {
        if (prescription == null) {
            return null;
        }

        DoctorDTO doctorDTO = null;
        if (prescription.getDoctor() != null) {
            doctorDTO = new DoctorDTO(
                    prescription.getDoctor().getName(),
                    prescription.getDoctor().getBmdcId()
            );
        }

        PatientDTO patientDTO = null;
        if (prescription.getPatient() != null) {
            patientDTO = new PatientDTO(
                    prescription.getPatient().getName(),
                    prescription.getPatient().getAge()
            );
        }

        List<DrugDTO> drugDTOs = prescription.getDrugs().stream()
                .map(drug -> new DrugDTO(drug.getDrugName(), drug.getDrugFormation(), drug.getDrugStrength()))
                .collect(Collectors.toList());

        return new PrescriptionDTO(
                prescription.getId(),
                doctorDTO,
                patientDTO,
                drugDTOs
        );
    }
}