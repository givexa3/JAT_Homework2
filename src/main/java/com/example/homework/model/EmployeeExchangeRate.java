package com.example.homework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "exchangeRate")
public class EmployeeExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "iso_Code_From")
    private String isoCodeFrom = "USD";

    @Column(name = "iso_Code_To")
    private String isoCodeTo = "GEL";
}
