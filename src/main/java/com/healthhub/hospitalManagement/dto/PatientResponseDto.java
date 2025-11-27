package com.healthhub.hospitalManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private com.healthhub.hospitalManagement.entity.type.BloodGroupType bloodGroup;
}
