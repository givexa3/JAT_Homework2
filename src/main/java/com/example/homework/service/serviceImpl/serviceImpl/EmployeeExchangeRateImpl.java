package com.example.homework.service.serviceImpl.serviceImpl;

import com.example.homework.dtos.EmployeeExchangeRateDTO;
import com.example.homework.mapper.EmployeeExchangeRateMapper;
import com.example.homework.model.EmployeeExchangeRate;
import com.example.homework.repository.EmployeeExchangeRateRepository;
import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class EmployeeExchangeRateImpl implements EmployeeExchangeRateService {

    private EmployeeExchangeRateRepository exchangeRateRepository;
    private EmployeeExchangeRateMapper employeeExchangeRateMapper;

    public EmployeeExchangeRateImpl(EmployeeExchangeRateRepository exchangeRateRepository,
                                    EmployeeExchangeRateMapper employeeExchangeRateMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.employeeExchangeRateMapper = employeeExchangeRateMapper;
    }

    @Override
    public void addExchangeRate(Calendar date, double rate) {
        EmployeeExchangeRateDTO e = new EmployeeExchangeRateDTO();
        e.setDate(date);
        e.setRate(rate);
        exchangeRateRepository.save(employeeExchangeRateMapper.dtoToEntity(e));

    }
}
