package com.example.homework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Getter
@Setter
@Table(name = "ExchangeRate")
public class EmployeeExchangeRate extends BaseEntity {


    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Calendar date;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "isoCodeFrom")
    private String isoCodeFrom = "USD";

    @Column(name = "isoCodeTo")
    private String isoCodeTo = "GEL";
}
