package com.rko.rxlocate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDetailsDTO {

    private String drugName;

    private String drugFormation;

    private String drugStrength;

    private String generic;

    private String vendor;

}
