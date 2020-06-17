package com.crud.companyemployee.service;

import com.crud.companyemployee.dto.EmployeeDto;
import com.crud.companyemployee.entity.Employee;
import com.crud.companyemployee.error.EmployeeDetailException;
import com.crud.companyemployee.repository.EmployeeDetailRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDetailServiceTest {

    @Mock
    private EmployeeDetailRepository employeeDetailRepository;

    private EmployeeDetailService employeeDetailService;

    @Before
    public void setUp() {
        employeeDetailService = new EmployeeDetailService(employeeDetailRepository);
    }

    @Test
    public void shouldGetEmployeeDataSuccessfully() {
        final long employeeId = 1L;
        Employee employee = new Employee();
        when(employeeDetailRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        final EmployeeDto resultEmployeeDetails = employeeDetailService.getEmployeeData(employeeId);
        assertNotNull(resultEmployeeDetails);

        verify(employeeDetailRepository, times(1)).findById(employeeId);
    }

    @Test(expected = EmployeeDetailException.class)
    public void shouldThrowEmployeeDetailException_WhenNoEmployeeDetailFound() {
        final long employeeId = 10101010L;
        Optional<Employee> optionalEmployee = Optional.empty();
        when(employeeDetailRepository.findById(employeeId)).thenReturn(optionalEmployee);

        final EmployeeDto resultEmployeeDetails = employeeDetailService.getEmployeeData(employeeId);
        assertNotNull(resultEmployeeDetails);

        verify(employeeDetailRepository, times(1)).findById(employeeId);
    }

    @Test
    public void shouldGetAllEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        when(employeeDetailRepository.findAll()).thenReturn(employees);

        final List<EmployeeDto> resultEmployeeData = employeeDetailService.getAllEmployeeData();
        assertNotNull(resultEmployeeData);

        verify(employeeDetailRepository, times(1)).findAll();
    }

    @Test
    public void shouldSaveEmployeeData() {
        Employee employee = new Employee();
        //when(employeeDetailRepository.save(any(Employee.class))).thenReturn();
        //final EmployeeDto resultEmployeeDto = employeeDetailService.saveEmployeeData(employee);

    }


    @After
    public void tearDown() {
        employeeDetailService = null;
    }
}
