package com.example.homework.repository;

import com.example.homework.model.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {

    @Query("select m from EmployeeSalary m where m.month = ?1")
    List<EmployeeSalary> findBySalaryMonth(int month);

    @Query("select m from EmployeeSalary m where m.fullName = ?1")
    List<EmployeeSalary> findByFullName(String fullName);

}
