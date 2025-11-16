package com.healthhub.hospitalManagement;


import com.healthhub.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.entity.type.BloodGroupType;
import com.healthhub.hospitalManagement.repository.PatientRepository;
import com.healthhub.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatienRepo()
    {
        List<Patient>myList = patientRepository.findAllPatientWithAppointment();
        System.out.println(myList);
    }

    @Test
    public void testTransactionMethods()
    {
        Patient patient = patientService.getPatientById(1L);
        List<Patient>myList = patientRepository.findBornAfterDate(LocalDate.of(1990,05,10));
        System.out.println("Test 1 : ");

        for (int i = 0; i < myList.size(); i++) {

            // Print all elements of List
            System.out.println(myList.get(i));
        }

    }

    @Test
    public void testTransactionMethodsQueryAnnotation()
    {
        Patient patient = patientService.getPatientById(1L);
        List<Patient>myList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
        System.out.println("Test 2 : ");

        for (int i = 0; i < myList.size(); i++) {

            // Print all elements of List
            System.out.println(myList.get(i));
        }
    }

    @Test
    public void test3()
    {
        List<Object[]>myList = patientRepository.countEachBloodGroupType();
        System.out.println("Test 3 : ");

        for (int i = 0; i < myList.size(); i++) {
            // Print all elements of List
            System.out.println(myList.get(i)[0] + "\t" + myList.get(i)[1]);
        }
    }

    @Test
    public void test4()
    {
        List<Object[]>myList = patientRepository. getAllPatientData();
        System.out.println("Test 4 : ");

        for (int i = 0; i < myList.size(); i++) {
            for (int j = 0; j < myList.get(i).length; j++) {
                    System.out.print(myList.get(i)[j] + "\t\t\t");
            }
            System.out.println();
        }
    }


    @Test
    public void test5()
    {
        System.out.println("Test 5 : ");
        System.out.println(patientRepository.updateNameWithId("Arnav Sharma" , 1L));
    }

    @Test
    public void test6()
    {
        System.out.println("Test 6 : ");
        List<BloodGroupCountResponseEntity> bloodGroupCountResponseEntityList=
                patientRepository.countEachBloodGroupType2();
        for (BloodGroupCountResponseEntity e : bloodGroupCountResponseEntityList) {
            System.out.println(e.getBloodGroupType() + "\t" + e.getCount());
        }
    }

    @Test
    public void test7()
    {
        System.out.println("Test 7 : ");

        // Data on Page no.3
        System.out.println("Page 3 : ");
        Page<Patient> patientList =
                patientRepository.findAllPatients(PageRequest.of(2,4) );
        for (Patient p : patientList)
        {
            System.out.println(p);
        }

        // Data on Page no.1 sorted by name
        System.out.println("Page 1 : ");
        patientList =
                patientRepository.findAllPatients(PageRequest.of(0,5 , Sort.by("name")));

        for (Patient p : patientList)
        {
            System.out.println(p);
        }
    }

}
