package com.example.homework.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public interface EmployeeExchangeRateService {
    void addExchangeRate(Calendar date, double rate);
}
