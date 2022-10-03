package com.example.homework.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@SpringBootTest
class EmployeeExchangeRateServiceTest{

    @Autowired
    private EmployeeExchangeRateService employeeExchangeRateService;

    @Test
    void addExchangeRateTest(){

        final Year YEAR = Year.of(2022);
        final Month MONTH = Month.DECEMBER;
        final int DAY = 31;
        final double EXCHANGE_RATE = 0;

        LocalDate date = LocalDate.of(YEAR.getValue(), MONTH, DAY);

        if(!employeeExchangeRateService.checkIfExchangeRateExists(date, EXCHANGE_RATE)){
            employeeExchangeRateService.addExchangeRate(date, EXCHANGE_RATE);
        }

        assertTrue(employeeExchangeRateService.checkIfExchangeRateExists(date, EXCHANGE_RATE));

    }

    @Test
    void getExchangeRateTest(){

        final double EXCHANGE_RATE = 2.945;
        final Month MONTH = Month.MAY;

        assertEquals(EXCHANGE_RATE, employeeExchangeRateService.getExchangeRate(MONTH), 1);

    }

    @Test
    void checkIfExchangeRateExistsTest(){

        final double EXCHANGE_RATE = 3.39;
        final Year YEAR = Year.of(2022);
        final Month MONTH = Month.MARCH;
        final int DAY = 7;

        LocalDate date = LocalDate.of(YEAR.getValue(), MONTH, DAY);

        assertTrue(employeeExchangeRateService.checkIfExchangeRateExists(date, EXCHANGE_RATE));

    }
}