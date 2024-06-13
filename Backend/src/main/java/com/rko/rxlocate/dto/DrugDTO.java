package com.rko.rxlocate.dto;

import com.rko.rxlocate.domain.Drug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String drugName;

    private String drugFormation;

    private String drugStrength;

    public static DrugDTO from(Drug drug) {
        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setDrugName(drug.getDrugName());
        drugDTO.setDrugFormation(drug.getDrugFormation());
        drugDTO.setDrugStrength(drug.getDrugStrength());
        return drugDTO;
    }
}