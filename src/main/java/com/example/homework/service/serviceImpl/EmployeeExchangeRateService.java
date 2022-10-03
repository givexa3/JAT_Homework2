package com.example.homework.service.serviceImpl;

import java.time.LocalDate;
import java.time.Month;

public interface EmployeeExchangeRateService {
    void addExchangeRate(LocalDate date, double rate);
    double getExchangeRate(Month month);
    boolean checkIfExchangeRateExists(LocalDate date, double rate);
}
