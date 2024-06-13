package com.rko.rxlocate.dto;

import com.rko.rxlocate.domain.Division;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DivisionDTO {

    private Long divisionId;

    private String divisionName;

    private String drugName;

    private long prescriptionCount;

    private double lat;

    private double lng;

    public static DivisionDTO from(Division division) {

        DivisionDTO div = new DivisionDTO();
        div.divisionName = division.getName();
        div.lat = division.getLat();
        div.lng = division.getLng();
        return div;
    }


}
