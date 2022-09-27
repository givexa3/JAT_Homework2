package com.example.homework.service.serviceImpl.serviceImpl;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.mapper.EmployeeSalaryMapper;
import com.example.homework.model.EmployeeExchangeRate;
import com.example.homework.model.EmployeeSalary;
import com.example.homework.repository.EmployeeExchangeRateRepository;
import com.example.homework.repository.EmployeeSalaryRepository;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeSalaryImpl implements EmployeeSalaryService {

    private EmployeeSalaryRepository employeeSalaryRepository;
    private EmployeeExchangeRateRepository exchangeRateRepository;

    private EmployeeSalaryMapper employeeSalaryMapper;

    public EmployeeSalaryImpl(EmployeeSalaryRepository employeeSalaryRepository,
                              EmployeeExchangeRateRepository exchangeRateRepository,
                              EmployeeSalaryMapper employeeSalaryMapper) {
        this.employeeSalaryRepository = employeeSalaryRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.employeeSalaryMapper = employeeSalaryMapper;
    }


    @Override
    public void addEmployeeSalary(String fullName, double salary, int month, int year) {
        EmployeeSalaryDTO e = new EmployeeSalaryDTO();
        e.setFullName(fullName);
        e.setSalary(salary);
        e.setMonth(month);
        e.setYear(year);
        employeeSalaryRepository.save(employeeSalaryMapper.dtoToEntity(e));
    }

    @Override
    public List<EmployeeSalaryDTO> getAllEmployeeSalaryInGEL(int month) {

        return employeeSalaryRepository.findBySalaryMonth(month)
                .stream()
                .map(e -> {
                    e.setSalary(getExchangeRate(month) * e.getSalary());
                    return e;
                })
                .map(e -> employeeSalaryMapper.entityToDto(e))
                .collect(Collectors.toList());

    }

    @Override
    public List<EmployeeMonthAVGSalaryDTO> getAllMonthAverageSalaryInGel() {

        final int FIRST_MONTH = 1;
        final int LAST_MONTH = 12;

        List<EmployeeMonthAVGSalaryDTO> employeeMonthSalaries = new ArrayList<>();

        for (int i = FIRST_MONTH; i <= LAST_MONTH; i++) {

            if(checkIfSalaryMonthExists(i)){

                double exchangeRate = getExchangeRate(i);
                double averageOfGivenMonth =
                        employeeSalaryRepository
                                .findBySalaryMonth(i)
                                .stream()
                                .mapToDouble(e -> e.getSalary() * exchangeRate)
                                .average()
                                .orElse(0);

                EmployeeMonthAVGSalaryDTO e = new EmployeeMonthAVGSalaryDTO();
                e.setMonth(i);
                e.setAverageMonthSalaryGel(averageOfGivenMonth);
                employeeMonthSalaries.add(e);

            }

        }
        return employeeMonthSalaries;
    }

    private double getExchangeRate(int month) {

        int exchangeRateDay = 7;
        final int EXCHANGE_RATE_DOWN = 0;

        Calendar cal = Calendar.getInstance();

        for (int i = exchangeRateDay; i > EXCHANGE_RATE_DOWN ; i--) {

            cal.set(2022, month-1, exchangeRateDay);

            Optional<EmployeeExchangeRate> foundExchangeRate =
                    Optional.ofNullable(exchangeRateRepository.findByDate(cal));

            if(foundExchangeRate.isPresent()){
                return foundExchangeRate.get().getRate();
            }

            exchangeRateDay--;
        }

        return 0;
    }

    private boolean checkIfSalaryMonthExists(int month){
        Optional<List<EmployeeSalary>> foundEmployeeSalary =
                Optional.ofNullable(employeeSalaryRepository.findBySalaryMonth(month));

        // should be re implemented :D
        if(foundEmployeeSalary.get().size() > 0){
            return true;
        }

        return false;
    }

}
