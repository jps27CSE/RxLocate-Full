package com.rko.rxlocate.dto;

import com.rko.rxlocate.domain.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private String name;

    private String bmdc;

    public static DoctorDTO from(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        return new DoctorDTO(doctor.getName(), doctor.getBmdcId());
    }
}