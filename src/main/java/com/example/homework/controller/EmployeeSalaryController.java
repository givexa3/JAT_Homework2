package com.example.homework.controller;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;


@RestController
@RequestMapping(EmployeeSalaryController.BASE_URL)
public class EmployeeSalaryController {

    public static final String BASE_URL = "/employees";
    private final EmployeeSalaryService employeeSalary;

    public EmployeeSalaryController(EmployeeSalaryService employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @GetMapping("/salaries/months/{month}")
    public ResponseEntity<List<EmployeeSalaryDTO>> getSalaryInGel(@PathVariable Month month) {
        return new ResponseEntity<>(employeeSalary.getAllEmployeeSalaryInGEL(month), HttpStatus.OK);
    }

    @GetMapping("/salaries/months/averages")
    public ResponseEntity<List<EmployeeMonthAVGSalaryDTO>> getAllMonthAVGSalaries()  {
        return new ResponseEntity<>(employeeSalary.getAllMonthAverageSalaryInGel(), HttpStatus.OK);
    }


}
