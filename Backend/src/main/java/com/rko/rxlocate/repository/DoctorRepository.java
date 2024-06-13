package com.rko.rxlocate.repository;

import com.rko.rxlocate.domain.Doctor;
import com.rko.rxlocate.dto.DoctorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByBmdcId(String bmdcId);

    @Query(value = "SELECT DISTINCT d.name AS doctorName, " +
            "                d.bmdc AS doctorBMDC, " +
            "                divi.name AS divisionName " +
            "FROM prescriptions p " +
            "         JOIN areas ar on p.area_id = ar.id " +
            "         JOIN districts dis on district_id = dis.id " +
            "         JOIN divisions divi on division_id = divi.id " +
            "         JOIN doctors d on d.id = p.doctor_id " +
            "         JOIN prescription_drugs pd on p.id = pd.prescription_id " +
            "         JOIN drugs dr on dr.id = pd.drug_id " +
            "WHERE dr.drug_name = :drugName ", nativeQuery = true)
    List<DoctorProjection> findDoctorsWithDivisionByDrug(@Param("drugName") String drugName);

    @Query(value = "SELECT DISTINCT d.name AS doctorName, " +
            "                d.bmdc AS doctorBMDC, " +
            "                divi.name AS divisionName " +
            "FROM prescriptions p " +
            "         JOIN areas ar on p.area_id = ar.id " +
            "         JOIN districts dis on ar.district_id = dis.id " +
            "         JOIN divisions divi on dis.division_id = divi.id " +
            "         JOIN doctors d on d.id = p.doctor_id " +
            "         JOIN prescription_drugs pd on p.id = pd.prescription_id " +
            "         JOIN drugs dr on dr.id = pd.drug_id " +
            "WHERE dr.id = :drugId", nativeQuery = true)
    List<DoctorProjection> findDoctorsWithDivisionByDrugId(@Param("drugId") Long drugId);

    @Query(value = "SELECT DISTINCT d.name AS doctorName, " +
            "                d.bmdc AS doctorBMDC, " +
            "                divi.name AS divisionName " +
            "FROM prescriptions p " +
            "         JOIN areas ar on p.area_id = ar.id " +
            "         JOIN districts dis on ar.district_id = dis.id " +
            "         JOIN divisions divi on dis.division_id = divi.id " +
            "         JOIN doctors d on d.id = p.doctor_id " +
            "         JOIN prescription_drugs pd on p.id = pd.prescription_id " +
            "         JOIN drugs dr on dr.id = pd.drug_id " +
            "WHERE divi.name = :divisionName and dr.drug_name = :drugName", nativeQuery = true)
    List<DoctorProjection> findDoctorsWithDivisionNameByDrugName(@Param("divisionName") String  divisionName, @Param("drugName") String  drugName);


    @Query(value = "SELECT DISTINCT d.name AS doctorName, " +
            "                d.bmdc AS doctorBMDC, " +
            "                divi.name AS divisionName " +
            "FROM prescriptions p " +
            "         JOIN areas ar on p.area_id = ar.id " +
            "         JOIN districts dis on ar.district_id = dis.id " +
            "         JOIN divisions divi on dis.division_id = divi.id " +
            "         JOIN doctors d on d.id = p.doctor_id " +
            "         JOIN prescription_drugs pd on p.id = pd.prescription_id " +
            "         JOIN drugs dr on dr.id = pd.drug_id " +
            "WHERE divi.id = :divisionId and dr.id = :drugId", nativeQuery = true)
    List<DoctorProjection> findDoctorsWithDivisionIdByDrugId(@Param("divisionId") Long divisionId, @Param("drugId") Long drugId);
}



