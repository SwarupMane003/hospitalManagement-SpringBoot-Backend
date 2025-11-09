package com.healthhub.hospitalManagement.service;

import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
//
//    public PatientService(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

    @Transactional
    public Patient getPatientById(Long id)
    {
        Patient p1 = patientRepository.findById(id).orElseThrow();
        Patient p2 = patientRepository.findById(id).orElseThrow();
        return p1;

    }



}
