package com.example.homework.repositoryTests;

import com.example.homework.model.EmployeeExchangeRate;
import com.example.homework.model.EmployeeSalary;
import com.example.homework.repository.EmployeeExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeExchangeRateRepositoryTest {

    @Autowired
    private EmployeeExchangeRateRepository exchangeRateRepository;

    @Test
    void findByDate() {

        final int YEAR = 2022;
        final int MONTH = 3;
        final int DAY = 7;
        final double EXCHANGE_RATE = 3.39;

        Calendar cal = Calendar.getInstance();
        cal.set(YEAR, MONTH-1, DAY);

        Optional<EmployeeExchangeRate> foundExchangeRate =
                Optional.ofNullable(exchangeRateRepository.findByDate(cal));

        if(foundExchangeRate.isPresent()){
            assertEquals(EXCHANGE_RATE, foundExchangeRate.get().getRate());
        }
        else{
            throw new NullPointerException("No Exchange Rate is present in DB with given Date time");
        }

    }


}