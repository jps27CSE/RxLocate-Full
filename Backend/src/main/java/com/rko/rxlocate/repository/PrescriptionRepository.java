package com.rko.rxlocate.repository;

import com.rko.rxlocate.domain.Prescription;
import com.rko.rxlocate.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT new com.rko.rxlocate.dto.DivisionDTO(divi.id, divi.name, dr.drugName, COUNT(p), divi.lat, divi.lng) " +
            "FROM Prescription p " +
            "JOIN p.area ar " +
            "JOIN ar.district dis " +
            "JOIN dis.division divi " +
            "JOIN p.drugs dr " +
            "WHERE dr.drugName LIKE %:drugName%  " +
            "GROUP BY divi.name, dr.drugName " +
            "ORDER BY COUNT(p) DESC")
    List<DivisionDTO> findDivisionsByDrugName(@Param("drugName") String drugName,
                                              Pageable pageable);

    @Query("SELECT new com.rko.rxlocate.dto.DivisionDTO(divi.id, divi.name, dr.drugName, COUNT(p), divi.lat, divi.lng) " +
            "FROM Prescription p " +
            "JOIN p.area ar " +
            "JOIN ar.district dis " +
            "JOIN dis.division divi " +
            "JOIN p.drugs dr " +
            "WHERE dr.id = :drugId " +
            "GROUP BY divi.id, dr.id " +
            "ORDER BY COUNT(p) DESC")
    List<DivisionDTO> findDivisionsByDrugId(@Param("drugId") Long drugId,
                                            Pageable pageable);


    @Query("SELECT new com.rko.rxlocate.dto.DistrictDTO(dis.id, d.name, dis.name, dr.drugName, COUNT(p), dis.lat, dis.lng) " +
            "FROM Prescription p " +
            "JOIN p.area ar " +
            "JOIN ar.district dis " +
            "JOIN dis.division d " +
            "JOIN p.drugs dr " +
            "WHERE dr.drugName = :drugName AND d.name = :divisionName " +
            "GROUP BY dis.name, dr.drugName, dis.lat, dis.lng " +
            "ORDER BY COUNT(p) DESC")
    List<DistrictDTO> findDistrictsByDivisionAndDrugName(@Param("drugName") String drugName,
                                                         @Param("divisionName") String divisionName,
                                                         Pageable pageable);

    @Query("SELECT new com.rko.rxlocate.dto.DistrictDTO(dis.id, d.name, dis.name, dr.drugName, COUNT(p), dis.lat, dis.lng) " +
            "FROM Prescription p " +
            "JOIN p.area ar " +
            "JOIN ar.district dis " +
            "JOIN dis.division d " +
            "JOIN p.drugs dr " +
            "WHERE dr.id = :drugId AND d.id = :divisionId " +
            "GROUP BY dis.id, d.name, dr.id, dis.name, dis.lat, dis.lng " +
            "ORDER BY COUNT(p) DESC")
    List<DistrictDTO> findDistrictsByDivisionAndDrugId(@Param("drugId") Long drugId,
                                                       @Param("divisionId") Long divisionId,
                                                       Pageable pageable);

    @Query("SELECT new com.rko.rxlocate.dto.AreaDTO(ar.id, dis.name, ar.name, dr.drugName, COUNT(p), ar.lat, ar.lng) " +
            "FROM Prescription p " +
            "JOIN p.area ar " +
            "JOIN ar.district dis " +
            "JOIN dis.division d " +
            "JOIN p.drugs dr " +
            "WHERE dr.drugName LIKE %:drugName%  AND d.name = :divisionName AND dis.name = :districtName " +
            "GROUP BY ar.id, dis.name, ar.name, dr.drugName, ar.lat, ar.lng " +
            "ORDER BY COUNT(p) DESC")
    List<AreaDTO> findAreasByDivisionAndDistrictAndDrugName(@Param("drugName") String drugName,
                                                            @Param("divisionName") String divisionName,
                                                            @Param("districtName") String districtName,
                                                            Pageable pageable);

    @Query("SELECT new com.rko.rxlocate.dto.AreaDTO(ar.id, dis.name, ar.name, dr.drugName, COUNT(p), ar.lat, ar.lng) " +
            "FROM Prescription p " +
            "JOIN p.area ar " +
            "JOIN ar.district dis " +
            "JOIN dis.division d " +
            "JOIN p.drugs dr " +
            "WHERE dr.id = :drugId AND d.id = :divisionId AND dis.id = :districtId " +
            "GROUP BY ar.id, dis.name, ar.name, dr.drugName, ar.lat, ar.lng " +
            "ORDER BY COUNT(p) DESC")
    List<AreaDTO> findAreasByDivisionAndDistrictAndDrugId(@Param("drugId") Long drugId,
                                                          @Param("divisionId") Long divisionId,
                                                          @Param("districtId") Long districtId,
                                                          Pageable pageable);

    @Query(value = "SELECT " +
            "    divi.name AS divisionName, " +
            "    COUNT(DISTINCT p.id) AS prescriptionCount, " +
            "    dr.drug_name AS drugName " +
            "FROM prescriptions p " +
            "JOIN areas ar ON p.area_id = ar.id " +
            "JOIN prescription_drugs dp ON p.id = dp.prescription_id " +
            "JOIN drugs dr ON dr.id = dp.drug_id " +
            "JOIN districts dis ON ar.district_id = dis.id " +
            "JOIN divisions divi ON dis.division_id = divi.id " +
            "WHERE dr.drug_name = :drugName " +
            "GROUP BY divi.name, dr.drug_name " +
            "ORDER BY prescriptionCount DESC", nativeQuery = true)
    List<DivisionPrescriptionProjection> excelDataByDrugName(@Param("drugName") String drugName);

    @Query(value = "SELECT " +
            "    divi.name AS divisionName, " +
            "    COUNT(DISTINCT p.id) AS prescriptionCount, " +
            "    dr.drug_name AS drugName " +
            "FROM prescriptions p " +
            "JOIN areas ar ON p.area_id = ar.id " +
            "JOIN prescription_drugs dp ON p.id = dp.prescription_id " +
            "JOIN drugs dr ON dr.id = dp.drug_id " +
            "JOIN districts dis ON ar.district_id = dis.id " +
            "JOIN divisions divi ON dis.division_id = divi.id " +
            "WHERE dr.id = :drugId " +
            "GROUP BY divi.name, dr.drug_name " +
            "ORDER BY prescriptionCount DESC", nativeQuery = true)
    List<DivisionPrescriptionProjection> excelDataByDrugId(@Param("drugId") Long drugId);

    @Query(value = "SELECT " +
            "    dis.name AS districtName, " +
            "    COUNT(DISTINCT p.id) AS prescriptionCount " +
            "FROM prescriptions p " +
            "JOIN areas ar ON p.area_id = ar.id " +
            "JOIN prescription_drugs pd ON p.id = pd.prescription_id " +
            "JOIN drugs dr ON dr.id = pd.drug_id " +
            "JOIN districts dis ON ar.district_id = dis.id " +
            "JOIN divisions divi ON dis.division_id = dis.id " +
            "WHERE dr.id = :drugId  AND divi.id = :divisionId  " +
            "GROUP BY divi.id, ar.id " +
            "ORDER BY prescriptionCount DESC ", nativeQuery = true)
    List<DistrictPrescriptionProjection> excelDataByDrugNameAndDivisionName(@Param("drugId") Long drugId,
                                                                            @Param("divisionId") Long divisionId);
    @Query(value = "SELECT " +
            "    dis.name AS districtName, " +
            "    COUNT(DISTINCT p.id) AS prescriptionCount " +
            "FROM " +
            "    prescriptions p " +
            "JOIN " +
            "    areas a ON p.area_id = a.id " +
            "JOIN " +
            "    districts dis ON a.district_id = dis.id " +
            "JOIN " +
            "    divisions d ON dis.division_id = d.id " +
            "JOIN " +
            "    prescription_drugs dp ON p.id = dp.prescription_id " +
            "JOIN " +
            "    drugs dr ON dr.id = dp.drug_id " +
            "WHERE " +
            "    dr.drug_name = :drugName " +
            "    AND d.name = :divisionName " +
            "GROUP BY " +
            "    dis.id, dis.name ", nativeQuery = true)
    List<DistrictPrescriptionProjection> excelDataByDrugNameAndDivisionName(@Param("drugName") String drugName,
                                                                            @Param("divisionName") String divisionName);


}

