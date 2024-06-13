package com.rko.rxlocate.controller;

import com.rko.rxlocate.dto.AreaDTO;
import com.rko.rxlocate.dto.DistrictDTO;
import com.rko.rxlocate.dto.DivisionDTO;
import com.rko.rxlocate.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/{drugName}")
    public ResponseEntity<?> getDivisionsByDrugName(@PathVariable String drugName) {
        List<DivisionDTO> divisions = searchService.getDivisionsByDrugName(drugName);
        return ResponseEntity.ok(divisions);
    }

    @GetMapping("/by/{drugId}")
    public ResponseEntity<?> getDivisionsByDrugId(@PathVariable Long drugId) {
        List<DivisionDTO> divisions = searchService.getDivisionsByDrugId(drugId);
        return ResponseEntity.ok(divisions);
    }

    @GetMapping("/{drugName}/{divisionName}")
    public ResponseEntity<?> getDistrictsByDivisionAndDrugName(@PathVariable String drugName,
                                                               @PathVariable String divisionName) {
        List<DistrictDTO> districts = searchService.getDistrictsByDivisionAndDrugName(drugName, divisionName);
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/by/{drugId}/{divisionId}")
    public ResponseEntity<?> getDistrictsByDivisionAndDrugId(@PathVariable Long drugId,
                                                             @PathVariable Long divisionId) {
        List<DistrictDTO> districts = searchService.getDistrictsByDivisionAndDrugId(drugId, divisionId);
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/{drugName}/{divisionName}/{districtName}")
    public ResponseEntity<?> getAreasByDivisionAndDistrictAndDrugName(@PathVariable String drugName,
                                                                      @PathVariable String divisionName,
                                                                      @PathVariable String districtName) {
        List<AreaDTO> areas = searchService.getAreasByDivisionAndDistrictAndDrugName(drugName, divisionName, districtName);
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/by/{drugId}/{divisionId}/{districtId}")
    public ResponseEntity<?> getAreasByDivisionAndDistrictAndDrugId(@PathVariable Long drugId,
                                                                    @PathVariable Long divisionId,
                                                                    @PathVariable Long districtId) {
        List<AreaDTO> areas = searchService.getAreasByDivisionAndDistrictAndDrugId(drugId, divisionId, districtId);
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getPrescriptionSummaryByDrugOrDrugAndDivision(@RequestParam String drugName,
                                                                           @RequestParam(required = false) String divisionName) {
        List<?> mapSummary = searchService.mapSummary(drugName, divisionName);
        return ResponseEntity.ok(mapSummary);
    }
}
