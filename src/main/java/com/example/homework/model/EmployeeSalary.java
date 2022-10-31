package com.example.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;

@Entity
@Getter
@Setter
@ToString
@Table(name = "employeeSalary")
public class EmployeeSalary{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName", nullable = false, length = 60)
    private String fullName;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month month;

    @Column(name = "year", nullable = false)
    private Year year;
}
