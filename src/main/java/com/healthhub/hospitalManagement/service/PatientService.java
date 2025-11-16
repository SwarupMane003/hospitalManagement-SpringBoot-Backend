package com.healthhub.hospitalManagement.service;

import com.healthhub.hospitalManagement.dto.PatientResponseDto;
import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;
//
//    public PatientService(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

//    @Transactional
//    public Patient getPatientById(Long id)
//    {
//        Patient p1 = patientRepository.findById(id).orElseThrow();
//        Patient p2 = patientRepository.findById(id).orElseThrow();
//        return p1;
//
//    }

    @Transactional
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not " +
                "Found with id: " + patientId));
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
