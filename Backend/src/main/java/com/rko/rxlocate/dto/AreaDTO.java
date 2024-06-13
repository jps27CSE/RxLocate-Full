package com.rko.rxlocate.dto;

import com.rko.rxlocate.domain.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    private Long areaId;

    private String districtName;

    private String areaName;

    private String drugName;

    private long prescriptionCount;

    private double lat;

    private double lng;

    public static AreaDTO from(Area area, String drugName, long prescriptionCount, double lat, double lng) {
        AreaDTO dto = new AreaDTO();
        dto.areaId = area.getId();
        dto.districtName = dto.getDistrictName();
        dto.areaName = area.getName();
        dto.drugName = drugName;
        dto.prescriptionCount = prescriptionCount;
        dto.lat = lat;
        dto.lng = lng;
        return dto;
    }
}
