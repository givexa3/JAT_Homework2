package com.example.homework.controller;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.model.EmployeeSalary;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeSalaryController {

    private EmployeeSalaryService employeeSalary;

    public EmployeeSalaryController(EmployeeSalaryService employeeSalary) {
        this.employeeSalary = employeeSalary;
    }


    @GetMapping("/salary/{month}")
    public ResponseEntity<List<EmployeeSalaryDTO>> getSalaryInGel(@PathVariable int month) {
        return new ResponseEntity<>(employeeSalary.getAllEmployeeSalaryInGEL(month), HttpStatus.OK);
    }

    @GetMapping("/average")
    public ResponseEntity<List<EmployeeMonthAVGSalaryDTO>> getAllMonthAVGSalaries()  {
        return new ResponseEntity<>(employeeSalary.getAllMonthAverageSalaryInGel(), HttpStatus.OK);
    }


}
