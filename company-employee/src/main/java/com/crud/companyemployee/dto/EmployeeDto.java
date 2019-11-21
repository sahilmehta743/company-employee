package com.crud.companyemployee.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDto {
    private Long employeeId;

    private String employeeName;

    private String employeeDepartment;
}
