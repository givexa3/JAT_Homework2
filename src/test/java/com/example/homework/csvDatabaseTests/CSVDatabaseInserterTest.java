package com.example.homework.csvDatabaseTests;

import com.example.homework.model.EmployeeExchangeRate;
import com.example.homework.model.EmployeeSalary;
import com.example.homework.repository.EmployeeExchangeRateRepository;
import com.example.homework.repository.EmployeeSalaryRepository;
import com.example.homework.utils.CSVDatabaseInserter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CSVDatabaseInserterTest {

    @Autowired
    private CSVDatabaseInserter csvDatabaseInserter;

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeeExchangeRateRepository employeeExchangeRateRepository;

    @Test
    void InsertEmployeeSalaryIntoDatabase() {

        final String EMPLOYEE_NAME = "Jesse L. Begay";
        final long EMPLOYEE_ID = 1L;
        final Month MONTH = Month.MAY;
        final Year YEAR = Year.of(2022);
        final String RESOURCE = "EmployeeSalaryMay2022.csv";

        csvDatabaseInserter.insertEmployeeSalaryIntoDatabase(RESOURCE, MONTH, YEAR);

        Optional<EmployeeSalary> foundEmployeeSalary = employeeSalaryRepository.findById(EMPLOYEE_ID);

        if(foundEmployeeSalary.isPresent()){

            assertEquals(EMPLOYEE_NAME, foundEmployeeSalary.get().getFullName());

        }else{
           throw new NullPointerException("Employee-Salary with given ID does not exist");
        }

    }

    @Test
    void InsertExchangeRateIntoDatabase() {

        final double EXCHANGE_RATE = 2.945;
        final String RESOURCE = "HistoryExchangeReport.csv";

        csvDatabaseInserter.insertExchangeRateIntoDatabase(RESOURCE);

        Optional<EmployeeExchangeRate> foundExchangeRate = employeeExchangeRateRepository.findById(1L);

        if(foundExchangeRate.isPresent()){
            assertEquals(EXCHANGE_RATE, foundExchangeRate.get().getRate());
        }else{
            throw new NullPointerException("Employee-Exchange-Rate with given ID does not exist");
        }

    }

}