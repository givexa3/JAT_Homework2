package com.example.homework.utils;

import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

@Component
public class CSVDatabaseInserter  {
    private final EmployeeSalaryService employeeSalaryService;
    private final EmployeeExchangeRateService employeeExchangeRateService;

    public CSVDatabaseInserter(EmployeeSalaryService employeeSalaryService,
                               EmployeeExchangeRateService employeeExchangeRateService) {
        this.employeeSalaryService = employeeSalaryService;
        this.employeeExchangeRateService = employeeExchangeRateService;
    }

    public void insertEmployeeSalaryIntoDatabase(String csvFileName, Month month, Year year) {

        final String RESOURCE = "./src/main/resources/csv/" + csvFileName;
        final String SPLIT_BY = ",";
        String line = "";

        try(BufferedReader br = new BufferedReader(new FileReader(RESOURCE)))
        {
            String[] header = br.readLine().split(SPLIT_BY);

            if(!checkHeaderForEmployeeSalary(header)){
                throw new IOException("Given CSV file headers don't match the requirements");
            }

            while ((line = br.readLine()) != null)
            {
                String[] employee = line.split(SPLIT_BY);

                if(!employeeSalaryService.checkIfEmployeeExists(employee[0].replace("\"", ""), month, year)){
                    employeeSalaryService.addEmployeeSalary(employee[0].replace("\"", "")
                            , Double.parseDouble(employee[1])
                            , month
                            , year);
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void insertExchangeRateIntoDatabase(String csvFileName) {

        final String RESOURCE = "./src/main/resources/csv/" + csvFileName;
        final String SPLIT_BY = ",";
        String line = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try(BufferedReader br = new BufferedReader(new FileReader(RESOURCE)))
        {
            String[] header = br.readLine().split(SPLIT_BY);

            if(!checkHeaderForExchangeRate(header)){
                throw new IOException("Given CSV file headers don't match the requirements");
            }

            while ((line = br.readLine()) != null)
            {
                String[] exchangeRate = line.split(SPLIT_BY);
                LocalDate localDate = LocalDate.parse(exchangeRate[0].split(" ")[0].substring(1), formatter);

                if(!employeeExchangeRateService.checkIfExchangeRateExists(localDate, Double.parseDouble(exchangeRate[1]))){
                    employeeExchangeRateService.addExchangeRate(
                            localDate,
                            Double.parseDouble(exchangeRate[1]));
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private boolean checkHeaderForEmployeeSalary(String[] header){

        final String FIRST_HEADER = "Employee";
        final String SECOND_HEADER = "Salary";

        if(header.length != 2){
            return false;
        }

        return header[0].equals(FIRST_HEADER) || header[1].equals(SECOND_HEADER);
    }

    private boolean checkHeaderForExchangeRate(String[] header){

        final String FIRST_HEADER = "Date";
        final String SECOND_HEADER = "Rate";
        final String THIRD_HEADER = "ISO Code From";
        final String FOURTH_HEADER = "ISO Code To";

        if(header.length != 4){
            return false;
        }

        return header[0].equals(FIRST_HEADER)
                || header[1].equals(SECOND_HEADER)
                || header[2].equals(THIRD_HEADER)
                || header[3].equals(FOURTH_HEADER);
    }
}
