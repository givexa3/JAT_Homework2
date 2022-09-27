package com.example.homework.service.serviceImpl;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;

import java.util.List;

public interface EmployeeSalaryService {
    void addEmployeeSalary(String fullName, double salary, int month, int year);

    List<EmployeeSalaryDTO> getAllEmployeeSalaryInGEL(int month);

    List<EmployeeMonthAVGSalaryDTO> getAllMonthAverageSalaryInGel();

}
