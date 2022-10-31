package com.example.homework.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMonthAVGSalaryDTO {
    private Month month;
    private BigDecimal averageMonthSalaryGel;
}
