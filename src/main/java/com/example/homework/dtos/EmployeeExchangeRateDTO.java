package com.example.homework.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeExchangeRateDTO {
    private Calendar date;
    private double rate;
    private String isoCodeFrom = "USD";
    private String isoCodeTo = "GEL";
}
