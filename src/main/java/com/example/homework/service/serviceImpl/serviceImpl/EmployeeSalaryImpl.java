package com.example.homework.service.serviceImpl.serviceImpl;

import com.example.homework.dtos.EmployeeMonthAVGSalaryDTO;
import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.mapper.EmployeeSalaryMapper;
import com.example.homework.model.EmployeeSalary;
import com.example.homework.repository.EmployeeSalaryRepository;
import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import com.example.homework.service.serviceImpl.EmployeeSalaryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeSalaryImpl implements EmployeeSalaryService {

    private final EmployeeSalaryRepository employeeSalaryRepository;
    private final EmployeeExchangeRateService employeeExchangeRateService;

    private final EmployeeSalaryMapper employeeSalaryMapper;

    public EmployeeSalaryImpl(EmployeeSalaryRepository employeeSalaryRepository,
                              EmployeeExchangeRateService employeeExchangeRateService,
                              EmployeeSalaryMapper employeeSalaryMapper) {
        this.employeeSalaryRepository = employeeSalaryRepository;
        this.employeeExchangeRateService = employeeExchangeRateService;
        this.employeeSalaryMapper = employeeSalaryMapper;
    }

    @Override
    public void addEmployeeSalary(String fullName, double salary, Month month, Year year) {
        EmployeeSalaryDTO employeeSalaryDTO = new EmployeeSalaryDTO();
        employeeSalaryDTO.setFullName(fullName);
        employeeSalaryDTO.setSalary(salary);
        employeeSalaryDTO.setMonth(month);
        employeeSalaryDTO.setYear(year);
        employeeSalaryRepository.save(employeeSalaryMapper.dtoToEntity(employeeSalaryDTO));
    }


    @Override
    public List<EmployeeSalaryDTO> getAllEmployeeSalaryInGEL(Month month) {

        return employeeSalaryRepository.findByMonth(month)
                .stream()
                .map(e -> {
                    e.setSalary(employeeExchangeRateService.getExchangeRate(month) * e.getSalary());
                    return e;
                })
                .map(employeeSalaryMapper::entityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<EmployeeMonthAVGSalaryDTO> getAllMonthAverageSalaryInGel() {

        final int FIRST_MONTH = 1;
        final int LAST_MONTH = 12;

        List<EmployeeMonthAVGSalaryDTO> employeeMonthSalaries = new ArrayList<>();

        for (int i = FIRST_MONTH; i <= LAST_MONTH; i++) {

            if(checkIfSalaryMonthExists(Month.of(i))){

                double exchangeRate = employeeExchangeRateService.getExchangeRate(Month.of(i));
                BigDecimal averageOfGivenMonth =
                        BigDecimal.valueOf(employeeSalaryRepository
                                .findByMonth(Month.of(i))
                                .stream()
                                .mapToDouble(e -> e.getSalary() * exchangeRate)
                                .average()
                                .orElse(0));

                EmployeeMonthAVGSalaryDTO employeeMonthAVGSalaryDTO = new EmployeeMonthAVGSalaryDTO();
                employeeMonthAVGSalaryDTO.setMonth(Month.of(i));
                employeeMonthAVGSalaryDTO.setAverageMonthSalaryGel(averageOfGivenMonth.setScale(2, RoundingMode.CEILING));
                employeeMonthSalaries.add(employeeMonthAVGSalaryDTO);

            }

        }
        return employeeMonthSalaries;
    }

    @Override
    public boolean checkIfEmployeeExists(String fullName, Month month, Year year) {
        return employeeSalaryRepository.existsByFullNameAndMonthAndYear(fullName, month, year);
    }

    private boolean checkIfSalaryMonthExists(Month month){
        Optional<List<EmployeeSalary>> foundEmployeeSalary =
                Optional.of(employeeSalaryRepository.findByMonth(month));

        return !foundEmployeeSalary.get().isEmpty();
    }

}
