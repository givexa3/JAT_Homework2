package com.example.homework.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMonthAVGSalaryDTO {
    private int month;
    private double averageMonthSalaryGel;
}
