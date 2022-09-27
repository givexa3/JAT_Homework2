package com.example.homework.repositoryTests;

import com.example.homework.model.EmployeeSalary;
import com.example.homework.repository.EmployeeSalaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeSalaryRepositoryTest {

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Test
    void findBySalaryMonth(){

        final int SALARY_MONTH = 3;
        final String EMPLOYEE_FULL_NAME = "Jesse L. Begay";
        final double EMPLOYEE_SALARY = 1500;
        final int EMPLOYEE_LIST_SIZE = 18;

        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findBySalaryMonth(SALARY_MONTH);

        assertEquals(EMPLOYEE_LIST_SIZE, employeeSalaryList.size());
        assertEquals(EMPLOYEE_FULL_NAME, employeeSalaryList.get(0).getFullName());
        assertEquals(EMPLOYEE_SALARY, employeeSalaryList.get(5).getSalary(), 0.001);
        assertEquals(0, employeeSalaryRepository.findBySalaryMonth(50).size());

    }

    @Test
    void findByFullName(){

        final String EMPLOYEE_NAME = "Jesse L. Begay";

        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findByFullName(EMPLOYEE_NAME);

        assertEquals(5, employeeSalaryList.size());
    }

}