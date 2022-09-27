package com.example.homework.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryDTO {
    private String fullName;
    private double salary;
    private int month;
    private int year;
}
