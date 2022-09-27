package com.example.homework.repository;

import com.example.homework.model.EmployeeExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;


public interface EmployeeExchangeRateRepository extends JpaRepository<EmployeeExchangeRate, Long> {
    EmployeeExchangeRate findByDate(Calendar date);

}
