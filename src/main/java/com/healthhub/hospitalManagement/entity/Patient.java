package com.healthhub.hospitalManagement.entity;

import com.healthhub.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(
        name = "patient_tbl",
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate")
        }
)
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length=40)
    private String name;
    private LocalDate birthDate;

    @Column(unique = true , nullable = false )
    private String email;
    private String gender;

    @CreationTimestamp
    @Column(updatable = false)   // without this also impossible to edit due to above timestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.ALL} , orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")   // owning side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE} , orphanRemoval = true , fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "Patient{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", birthDate=" + birthDate +
//                ", email='" + email + '\'' +
//                ", gender='" + gender + '\'' +
//                ", createdAt=" + createdAt +
//                ", bloodGroup=" + bloodGroup +
//                '}';
//    }
}
