package com.example.homework.serviceTests;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.mapper.EmployeeSalaryMapper;
import com.example.homework.repository.EmployeeSalaryRepository;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeSalaryServiceTest {

    @Autowired
    private EmployeeSalaryService employeeSalaryService;
    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeeSalaryMapper employeeSalaryMapper;

    @Test
    void addEmployeeSalary() {

        //Needs rollback for, because each time we should not create new instances in db
        //tried by can not do it ask experts

        EmployeeSalaryDTO e = new EmployeeSalaryDTO();
        e.setFullName("TEST");
        e.setSalary(0);
        e.setMonth(0);
        e.setYear(0);
        employeeSalaryRepository.save(employeeSalaryMapper.dtoToEntity(e));

        assertEquals(1, employeeSalaryRepository.findByFullName("TEST").size());

    }

    @Test
    void getAllEmployeeSalaryInGEL() {

        final int MONTH = 3;

        List<EmployeeSalaryDTO> employeeSalaryList = employeeSalaryService.getAllEmployeeSalaryInGEL(MONTH);

        assertEquals(3390, employeeSalaryList.get(0).getSalary());
        assertEquals(5085, employeeSalaryList.get(5).getSalary());
        assertEquals(10170, employeeSalaryList.get(10).getSalary());
    }

    @Test
    void getAllMonthAverageSalaryInGel() {

        List<EmployeeMonthAVGSalaryDTO> employeeMonthSalaries = employeeSalaryService.getAllMonthAverageSalaryInGel();

        //also change floating decimal to instead of 6233.833333 to 6233.83
        //also casting ins not good here try to make rounding 6233.83 of double..
        //but ask experts is changing floating decimal happening in backend or front end?
        assertEquals(6233, (int) employeeMonthSalaries.get(0).getAverageMonthSalaryGel());
        assertEquals(5797, (int) employeeMonthSalaries.get(1).getAverageMonthSalaryGel());
        assertEquals(5605, employeeMonthSalaries.get(3).getAverageMonthSalaryGel());
    }

}