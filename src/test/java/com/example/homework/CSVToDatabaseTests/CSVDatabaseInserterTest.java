package com.example.homework.CSVToDatabaseTests;

import com.example.homework.model.EmployeeExchangeRate;
import com.example.homework.model.EmployeeSalary;
import com.example.homework.repository.EmployeeExchangeRateRepository;
import com.example.homework.repository.EmployeeSalaryRepository;
import com.example.homework.utils.CSVDatabaseInserter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CSVDatabaseInserterTest {

    private static final String FULL_NAME_MAY = "C:\\Users\\Givexa\\Desktop\\CSV\\EmployeeSalaryMay2022.csv";

    private static final String FULL_NAME_EXCHANGE_RATE = "C:\\Users\\Givexa\\Desktop\\CSV\\HistoryExchangeReport.csv";

    @Autowired
    private CSVDatabaseInserter c;

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeeExchangeRateRepository employeeExchangeRateRepository;

    @Test
    void InsertEmployeeSalaryIntoDatabase(){

        final String EMPLOYEE_NAME = "Jesse L. Begay";
        final long EMPLOYEE_ID = 1L;
        //also needs rollback not to create new db everytime
        //as we see we can use autowired to run CSVreader, so try to sepearte logic from main and do it in regular class
        //maybe with postConstructor or commandLineReader....
        c.InsertEmployeeSalaryIntoDatabase(FULL_NAME_MAY, 3, 2022);

        Optional<EmployeeSalary> foundEmployeeSalary = employeeSalaryRepository.findById(EMPLOYEE_ID);

        if(foundEmployeeSalary.isPresent()){

            assertEquals(EMPLOYEE_NAME, foundEmployeeSalary.get().getFullName());

        }else{
           throw new NullPointerException("Employee-Salary with given ID does not exist");
        }

    }

    @Test
    void InsertExchangeRateIntoDatabase(){

        final double EXCHANGE_RATE = 2.945;

        //also needs rollback
        c.InsertExchangeRateIntoDatabase(FULL_NAME_EXCHANGE_RATE);

        Optional<EmployeeExchangeRate> foundExchangeRate = employeeExchangeRateRepository.findById(1L);

        if(foundExchangeRate.isPresent()){
            assertEquals(EXCHANGE_RATE, foundExchangeRate.get().getRate());
        }else{
            throw new NullPointerException("Employee-Exchange-Rate with given ID does not exist");
        }

    }

}