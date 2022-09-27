package com.example.homework.utils;

import com.example.homework.HomeworkApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class CSVReader {
    public static void main(String[] args) {

        //this code should only run once ask experts whats the best option to do so
        //instead of commenting it out every time we dont want it to be saved in db
        //also i think its not necessary to do this logic in main method so ask
        ApplicationContext applicationContext = SpringApplication.run(HomeworkApplication.class, args);
        CSVDatabaseInserter c = applicationContext.getBean(CSVDatabaseInserter.class);
//		c.InsertEmployeeSalaryIntoDatabase("C:\\Users\\Givexa\\Desktop\\CSV\\EmployeeSalaryMarch2022.csv", 3, 2022);
//        c.InsertEmployeeSalaryIntoDatabase("C:\\Users\\Givexa\\Desktop\\CSV\\EmployeeSalaryApril2022.csv", 4, 2022);
//        c.InsertEmployeeSalaryIntoDatabase("C:\\Users\\Givexa\\Desktop\\CSV\\EmployeeSalaryMay2022.csv", 5, 2022);
//        c.InsertEmployeeSalaryIntoDatabase("C:\\Users\\Givexa\\Desktop\\CSV\\EmployeeSalaryJune2022.csv", 6, 2022);
//        c.InsertEmployeeSalaryIntoDatabase("C:\\Users\\Givexa\\Desktop\\CSV\\EmployeeSalaryJuly2022.csv", 7, 2022);
//        c.InsertExchangeRateIntoDatabase("C:\\Users\\Givexa\\Desktop\\CSV\\HistoryExchangeReport.csv");
    }
}
