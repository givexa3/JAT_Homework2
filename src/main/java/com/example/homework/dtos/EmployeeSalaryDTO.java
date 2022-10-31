package com.example.homework.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryDTO {
    private String fullName;
    private double salary;
    private Month month;
    private Year year;
}
