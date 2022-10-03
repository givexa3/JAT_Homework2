package com.example.homework.service.serviceImpl;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface EmployeeSalaryService {
    void addEmployeeSalary(String fullName, double salary, Month month, Year year);
    List<EmployeeSalaryDTO> getAllEmployeeSalaryInGEL(Month month);
    List<EmployeeMonthAVGSalaryDTO> getAllMonthAverageSalaryInGel();
    boolean checkIfEmployeeExists(String fullName, Month month, Year year);
}
