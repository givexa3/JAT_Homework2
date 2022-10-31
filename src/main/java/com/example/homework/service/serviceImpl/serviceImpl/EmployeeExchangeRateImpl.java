package com.example.homework.service.serviceImpl.serviceImpl;

import com.example.homework.dtos.EmployeeExchangeRateDTO;
import com.example.homework.mapper.EmployeeExchangeRateMapper;
import com.example.homework.model.EmployeeExchangeRate;
import com.example.homework.repository.EmployeeExchangeRateRepository;
import com.example.homework.service.serviceImpl.EmployeeExchangeRateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Service
public class EmployeeExchangeRateImpl implements EmployeeExchangeRateService {

    private final EmployeeExchangeRateRepository exchangeRateRepository;
    private final EmployeeExchangeRateMapper employeeExchangeRateMapper;

    public EmployeeExchangeRateImpl(EmployeeExchangeRateRepository exchangeRateRepository,
                                    EmployeeExchangeRateMapper employeeExchangeRateMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.employeeExchangeRateMapper = employeeExchangeRateMapper;
    }

    @Override
    public void addExchangeRate(LocalDate date, double rate) {
        EmployeeExchangeRateDTO employeeExchangeRateDTO = new EmployeeExchangeRateDTO();
        employeeExchangeRateDTO.setDate(date);
        employeeExchangeRateDTO.setRate(rate);
        exchangeRateRepository.save(employeeExchangeRateMapper.dtoToEntity(employeeExchangeRateDTO));

    }

    @Override
    public double getExchangeRate(Month month) {

        int exchangeRateDay = 7;
        final int EXCHANGE_RATE_DOWN = 0;

        for (int i = exchangeRateDay; i > EXCHANGE_RATE_DOWN ; i--) {

            LocalDate localDate = LocalDate.of(2022, month, exchangeRateDay);

            Optional<EmployeeExchangeRate> foundExchangeRate =
                    Optional.ofNullable(exchangeRateRepository.findByDate(localDate));

            if(foundExchangeRate.isPresent()){
                return foundExchangeRate.get().getRate();
            }

            exchangeRateDay--;
        }

        throw new NullPointerException("Exchange rate was not found!");
    }

    @Override
    public boolean checkIfExchangeRateExists(LocalDate date, double rate) {
        return exchangeRateRepository.existsByDateAndRate(date, rate);
    }
}
