package com.crud.companyemployee.error;

public class EmployeeDetailException extends RuntimeException {
    private static final long serialVersionUID = -4279138901309408184L;

    public EmployeeDetailException(String errorMessage) {
        super(errorMessage);
    }
}
