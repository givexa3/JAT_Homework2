package com.example.homework.serviceTests;

import com.example.homework.repository.EmployeeExchangeRateRepository;
import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeExchangeRateServiceTest {

    @Autowired
    private EmployeeExchangeRateService employeeExchangeRateService;
    @Autowired
    private EmployeeExchangeRateRepository employeeExchangeRateRepository;

    @Test
    void addExchangeRate() {

        final int YEAR = 2022;
        final int MONTH = 12;
        final int DAY = 31;
        final double EXCHANGE_RATE = 0;

        Calendar cal = Calendar.getInstance();
        cal.set(YEAR, MONTH-1, DAY);

        //first you should do checking if current rate exist in DB :D then u should add

        //btw researc on runWith and springboottest annotation why we need irr
        //p.s needs roll back also to not create new exchange rate everytime...
        employeeExchangeRateService.addExchangeRate(cal, EXCHANGE_RATE);
        assertEquals(0, employeeExchangeRateRepository.findByDate(cal).getRate());

    }
}