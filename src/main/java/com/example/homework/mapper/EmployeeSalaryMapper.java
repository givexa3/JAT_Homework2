package com.example.homework.mapper;

import com.example.homework.dtos.EmployeeSalaryDTO;
import com.example.homework.model.EmployeeSalary;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSalaryMapper {
    private final ModelMapper modelMapper;

    public EmployeeSalaryMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public EmployeeSalaryDTO entityToDto(EmployeeSalary employeeSalary){
        return modelMapper.map(employeeSalary, EmployeeSalaryDTO.class);
    }

    public EmployeeSalary dtoToEntity(EmployeeSalaryDTO employeeSalaryDTO){
        return modelMapper.map(employeeSalaryDTO, EmployeeSalary.class);
    }
}
