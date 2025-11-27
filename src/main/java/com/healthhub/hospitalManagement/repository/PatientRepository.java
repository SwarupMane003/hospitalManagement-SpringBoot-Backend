package com.healthhub.hospitalManagement.repository;

import com.healthhub.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>
{
    // you can use List<Patient> , Optional<Patient>
    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate date ,String email);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bg);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("SELECT p.bloodGroup, count(p) FROM Patient p GROUP BY p.bloodGroup")
    List<Object[]> countEachBloodGroupType();


    @Query(value = "SELECT * FROM patient_tbl", nativeQuery = true )
    List<Object[]> getAllPatientData();

    @Modifying
    @Transactional
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name,@Param("id") Long id);

    @Query("SELECT new com.healthhub.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, count(p)) FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType2();

    @Query(value = "select * from patient_tbl", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    // N+1 Optimisation
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH p.insurance")
    List<Patient> findAllPatientWithAppointment();

    // if doctor not requred fetch type make LAZY
    //@Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments LEFT JOIN FETCH p.insurance")
    //List<Patient> findAllPatientWithAppointment();
}
