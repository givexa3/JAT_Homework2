package com.example.homework.controller;

import com.example.homework.utils.CSVDatabaseInserter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping(CsvDatabaseController.BASE_URL)
public class CsvDatabaseController {

    public static final String BASE_URL = "/import";

    private final CSVDatabaseInserter csvDatabaseInserter;

    public CsvDatabaseController(CSVDatabaseInserter csvDatabaseInserter) {
        this.csvDatabaseInserter = csvDatabaseInserter;
    }

    @PostMapping("/employees/salaries/{fileName}/{month}/{year}")
    public ResponseEntity<HttpStatus> insertSalariesInDb(@PathVariable String fileName,
                                                         @PathVariable Month month,
                                                         @PathVariable Year year) {
        csvDatabaseInserter.insertEmployeeSalaryIntoDatabase(fileName, month, year);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/employees/exchangeRates/{fileName}")
    public ResponseEntity<HttpStatus> insertExchangeRatesInDb(@PathVariable String fileName) {
        csvDatabaseInserter.insertExchangeRateIntoDatabase(fileName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
