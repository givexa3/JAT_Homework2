package com.example.homework.repository;

import com.example.homework.model.EmployeeExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;


public interface EmployeeExchangeRateRepository extends JpaRepository<EmployeeExchangeRate, Long> {
    EmployeeExchangeRate findByDate(LocalDate date);
    boolean existsByDateAndRate(LocalDate date, double rate);
}
