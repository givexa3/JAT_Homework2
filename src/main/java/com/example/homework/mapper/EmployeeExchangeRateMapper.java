package com.example.homework.mapper;

import com.example.homework.dtos.EmployeeExchangeRateDTO;
import com.example.homework.model.EmployeeExchangeRate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeExchangeRateMapper {
    private final ModelMapper modelMapper;

    public EmployeeExchangeRateMapper (ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public EmployeeExchangeRateDTO entityToDto(EmployeeExchangeRate employeeExchangeRate){
        return modelMapper.map(employeeExchangeRate, EmployeeExchangeRateDTO.class);
    }

    public EmployeeExchangeRate dtoToEntity(EmployeeExchangeRateDTO employeeExchangeRateDTO){
        return modelMapper.map(employeeExchangeRateDTO, EmployeeExchangeRate.class);
    }
}
