package com.healthhub.hospitalManagement;

import com.healthhub.hospitalManagement.entity.Insurance;
import com.healthhub.hospitalManagement.entity.Patient;
import com.healthhub.hospitalManagement.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    void test1()
    {
        Insurance insurance = Insurance.builder().policyNumber("HDFC_1234")
                .provider("HDFC").validUntil(LocalDate.of(2030,12,12)).build();


        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);
    }

}
