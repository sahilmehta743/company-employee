package com.crud.companyemployee.mapper;

import com.crud.companyemployee.dto.EmployeeDto;
import com.crud.companyemployee.entity.Employee;

public final class EmployeeEntityToDtoMapper {
    private EmployeeEntityToDtoMapper() {
        throw new AssertionError();
    }

    public static EmployeeDto map(final Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getId());
        employeeDto.setEmployeeName(employee.getName());
        employeeDto.setEmployeeDepartment(employee.getDepartment());

        return employeeDto;
    }
}
