package com.example.homework.repository;

import com.example.homework.model.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {
    List<EmployeeSalary> findByMonth(Month month);
    List<EmployeeSalary> findByFullName(String fullName);
    boolean existsByFullNameAndMonthAndYear(String fullName, Month month, Year year);
}
