package com.rko.rxlocate.dto;

import com.rko.rxlocate.domain.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private Long districtId;

    private String divisionName;

    private String districtName;

    private String drugName;

    private long prescriptionCount;

    private double lat;

    private double lng;

    public static DistrictDTO from(District district, String drugName, long prescriptionCount, double lat, double lng) {
        DistrictDTO dto = new DistrictDTO();
        dto.districtId = district.getId();
        dto.divisionName = dto.getDivisionName();
        dto.districtName = district.getName();
        dto.drugName = drugName;
        dto.prescriptionCount = prescriptionCount;
        dto.lat = lat;
        dto.lng = lng;
        return dto;
    }
}
