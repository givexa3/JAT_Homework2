package com.example.homework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "EmployeeSalary")
public class EmployeeSalary extends BaseEntity{

    @Column(name = "fullName", nullable = false, length = 60)
    private String fullName;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "salary_month", nullable = false)
    private int month;

    @Column(name = "salary_year", nullable = false)
    private int year;

    @Override
    public String toString() {
        return "EmployeeSalary{" +
                "ID: " + getId() +
                ", full_name = '" + getFullName() + '\'' +
                ", salary = " + getSalary() + '\'' +
                ", month = " + getMonth() + '\'' +
                ", year = " + getYear() + '\'' +
                '}';
    }

}
