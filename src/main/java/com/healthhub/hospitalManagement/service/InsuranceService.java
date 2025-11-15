package com.healthhub.hospitalManagement.service;

import com.healthhub.hospitalManagement.entity.Insurance;
import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.repository.InsuranceRepository;
import com.healthhub.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    //this will create insuarnace if it is not in db
    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance , Long patientId)
    {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found with Id : " + patientId));

        // patient becomes dirty
        patient.setInsurance(insurance);
        insurance.setPatient(patient); /// to maintain bidrectional consistancy

        return patient;
    }

    public Patient diassociateInsuranceFromPatient(Long patientId)
    {
        Patient patient = patientRepository.
                findById(patientId).
                orElseThrow(() -> new EntityNotFoundException("Patient Not Found with this id " + patientId));

        patient.setInsurance(null);

        return patient;
    }


}

