package com.crud.companyemployee.service;

import com.crud.companyemployee.dto.EmployeeDto;
import com.crud.companyemployee.entity.Employee;
import com.crud.companyemployee.error.EmployeeDetailException;
import com.crud.companyemployee.mapper.EmployeeEntityToDtoMapper;
import com.crud.companyemployee.repository.EmployeeDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeDetailService {

    private EmployeeDetailRepository employeeDetailRepository;

    @Autowired
    public EmployeeDetailService(EmployeeDetailRepository employeeDetailRepository) {
        this.employeeDetailRepository = employeeDetailRepository;
    }

    public EmployeeDto getEmployeeData(final @NotNull Long employeeId) {
        Optional<Employee> optionalEmployee = employeeDetailRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeDetailException("No employee found with employeeId : " + employeeId);
        }
        Employee employeeData = optionalEmployee.get();
        return EmployeeEntityToDtoMapper.map(employeeData);
    }

    public List<EmployeeDto> getAllEmployeeData() {
        List<Employee> employees = employeeDetailRepository.findAll();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = EmployeeEntityToDtoMapper.map(employee);
            employeesDto.add(employeeDto);
        }
        return employeesDto;
    }

    public EmployeeDto saveEmployeeData(final @NotNull EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getEmployeeName());
        employee.setDepartment(employeeDto.getEmployeeDepartment());

        Employee resultEmployee = employeeDetailRepository.save(employee);

        return EmployeeEntityToDtoMapper.map(resultEmployee);
    }

    public EmployeeDto updateEmployeeData(final @NotNull EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getEmployeeId());
        employee.setName(employeeDto.getEmployeeName());
        employee.setDepartment(employeeDto.getEmployeeDepartment());

        Employee resultEmployee = employeeDetailRepository.save(employee);

        return EmployeeEntityToDtoMapper.map(resultEmployee);
    }

    public String deleteEmployeeData(final @NotNull Long employeeId) {
        employeeDetailRepository.deleteById(employeeId);
        return "Employee SuccessFully Deleted";
    }
}
