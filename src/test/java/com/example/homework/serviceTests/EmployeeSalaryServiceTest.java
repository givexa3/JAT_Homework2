package com.example.homework.serviceTests;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.mapper.EmployeeSalaryMapper;
import com.example.homework.repository.EmployeeSalaryRepository;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeSalaryServiceTest {

    @Autowired
    private EmployeeSalaryService employeeSalaryService;
    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeeSalaryMapper employeeSalaryMapper;

    @Test
    void addEmployeeSalary() {

        final String FULL_NAME = "TEST";
        final double SALARY = 0;
        final Month MONTH = Month.MAY;
        final Year YEAR = Year.of(0);
        final int EXPECTED_VALUE = 1;

        if(!employeeSalaryService.checkIfEmployeeExists(FULL_NAME, MONTH, YEAR)){
            employeeSalaryService.addEmployeeSalary(FULL_NAME, SALARY, MONTH, YEAR);
        }

        assertEquals(EXPECTED_VALUE, employeeSalaryRepository.findByFullName(FULL_NAME).size());

    }

    @Test
    void getAllEmployeeSalaryInGEL() {

        final Month MONTH = Month.MARCH;

        List<EmployeeSalaryDTO> employeeSalaryList = employeeSalaryService.getAllEmployeeSalaryInGEL(MONTH);

        assertEquals(3390, employeeSalaryList.get(0).getSalary());
        assertEquals(5085, employeeSalaryList.get(5).getSalary());
        assertEquals(10170, employeeSalaryList.get(10).getSalary());
    }

    @Test
    void getAllMonthAverageSalaryInGel() {

        List<EmployeeMonthAVGSalaryDTO> employeeMonthSalaries = employeeSalaryService.getAllMonthAverageSalaryInGel();

        BigDecimal Expected = BigDecimal.valueOf(6233.84);

        assertEquals(Expected, employeeMonthSalaries.get(0).getAverageMonthSalaryGel());

    }

}
