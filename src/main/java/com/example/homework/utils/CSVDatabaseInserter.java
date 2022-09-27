package com.example.homework.utils;

import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
public class CSVDatabaseInserter  {

    private final String SPLIT_BY = ",";

    //should be changed to stringbuilder
    //but since file is not big its not a big issue
    private String line = "";

    private EmployeeSalaryService employeeSalaryService;
    private EmployeeExchangeRateService employeeExchangeRateService;

    public CSVDatabaseInserter(EmployeeSalaryService employeeSalaryService,
                               EmployeeExchangeRateService employeeExchangeRateService) {
        this.employeeSalaryService = employeeSalaryService;
        this.employeeExchangeRateService = employeeExchangeRateService;
    }


    public void InsertEmployeeSalaryIntoDatabase(String fullCSVName, int month, int year){
        try(BufferedReader br = new BufferedReader(new FileReader(fullCSVName)))
        {
            String[] header = br.readLine().split(SPLIT_BY);

            if(!checkHeaderForEmployeeSalary(header)){
                throw new IOException("Given CSV file headers don't match the requirements");
            }

            while ((line = br.readLine()) != null)
            {
                String[] employee = line.split(SPLIT_BY);
                employeeSalaryService.addEmployeeSalary(employee[0].replace("\"", ""), Double.parseDouble(employee[1]), month, year);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void InsertExchangeRateIntoDatabase(String fullCSVName){
        try(BufferedReader br = new BufferedReader(new FileReader(fullCSVName)))
        {
            String[] header = br.readLine().split(SPLIT_BY);

            if(!checkHeaderForExchangeRate(header)){
                throw new IOException("Given CSV file headers don't match the requirements");
            }

            while ((line = br.readLine()) != null)
            {
                String[] exchangeRate = line.split(SPLIT_BY);

                Calendar cal = Calendar.getInstance();
                cal.setTime(new SimpleDateFormat("MM/dd/yyyy")
                        .parse(exchangeRate[0].split(" ")[0].substring(1)));

                employeeExchangeRateService.addExchangeRate(
                        cal,
                        Double.parseDouble(exchangeRate[1]));

            }
        }
        catch (IOException | ParseException e)
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

        if(!header[0].equals(FIRST_HEADER) && !header[1].equals(SECOND_HEADER)){
            return false;
        }

        return true;
    }

    private boolean checkHeaderForExchangeRate(String[] header){

        final String FIRST_HEADER = "Date";
        final String SECOND_HEADER = "Rate";
        final String THIRD_HEADER = "ISO Code From";
        final String FOURTH_HEADER = "ISO Code To";

        if(header.length != 4){
            return false;
        }

        if(!header[0].equals(FIRST_HEADER)
                && !header[1].equals(SECOND_HEADER)
                && !header[2].equals(THIRD_HEADER)
                && !header[3].equals(FOURTH_HEADER)){
            return false;
        }

        return true;
    }
}
