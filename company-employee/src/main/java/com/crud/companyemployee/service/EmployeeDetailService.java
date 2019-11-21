package com.crud.companyemployee.service;

import com.crud.companyemployee.dto.EmployeeDto;
import com.crud.companyemployee.entity.Employee;
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

    public EmployeeDto getEmployeeData(final @NotNull Long employeeId) throws Exception {
        log.info("Inside class -> EmployeeDetailService method -> getEmployeeData()");

        Optional<Employee> optionalEmployee = employeeDetailRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            throw new Exception("No employee found with employeeId : " + employeeId);
        }
        Employee employeeData = optionalEmployee.get();

        log.info("Outside class -> EmployeeDetailService method -> getEmployeeData()");
        return EmployeeEntityToDtoMapper.map(employeeData);
    }

    public List<EmployeeDto> getAllEmployeeData() {
        log.info("Inside class -> EmployeeDetailService method -> getAllEmployeeData()");

        List<Employee> employees = employeeDetailRepository.findAll();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = EmployeeEntityToDtoMapper.map(employee);
            employeesDto.add(employeeDto);
        }

        log.info("Outside class -> EmployeeDetailService method -> getAllEmployeeData()");
        return employeesDto;
    }

    public EmployeeDto saveEmployeeData(final @NotNull EmployeeDto employeeDto) {
        log.info("Inside class -> EmployeeDetailService method -> saveEmployeeData()");

        Employee employee = new Employee();
        employee.setName(employeeDto.getEmployeeName());
        employee.setDepartment(employeeDto.getEmployeeDepartment());

        Employee resultEmployee = employeeDetailRepository.save(employee);

        log.info("Outside class -> EmployeeDetailService method -> saveEmployeeData()");
        return EmployeeEntityToDtoMapper.map(resultEmployee);
    }

    public EmployeeDto updateEmployeeData(final @NotNull EmployeeDto employeeDto) {
        log.info("Inside class -> EmployeeDetailService method -> updateEmployeeData()");

        Employee employee = new Employee();
        employee.setId(employeeDto.getEmployeeId());
        employee.setName(employeeDto.getEmployeeName());
        employee.setDepartment(employeeDto.getEmployeeDepartment());

        Employee resultEmployee = employeeDetailRepository.save(employee);

        log.info("Outside class -> EmployeeDetailService method -> updateEmployeeData()");
        return EmployeeEntityToDtoMapper.map(resultEmployee);
    }

    public String deleteEmployeeData(final @NotNull Long employeeId) {
        log.info("Inside class -> EmployeeDetailService method -> deleteEmployeeData()");

        employeeDetailRepository.deleteById(employeeId);

        log.info("Outside class -> EmployeeDetailService method -> deleteEmployeeData()");
        return "Employee SuccessFully Deleted";
    }
}
