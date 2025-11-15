package com.healthhub.hospitalManagement.service;


import com.healthhub.hospitalManagement.entity.Appointment;
import com.healthhub.hospitalManagement.entity.Doctor;
import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.repository.AppointmentRepository;
import com.healthhub.hospitalManagement.repository.DoctorRepository;
import com.healthhub.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId)
    {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have !!");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);   // to maintain bidrection consistency

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId , Long doctorId)
    {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will call update , dirty checking
        doctor.getAppointments().add(appointment);  // bidirectional  consistiency

        return appointment;
    }


}
