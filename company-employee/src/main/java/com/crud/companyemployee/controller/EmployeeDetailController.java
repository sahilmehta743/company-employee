package com.crud.companyemployee.controller;

import com.crud.companyemployee.dto.EmployeeDto;
import com.crud.companyemployee.service.EmployeeDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crud.companyemployee.constant.AppConstant.API_ROOT_URL;

@Slf4j
@RestController
@RequestMapping(value = API_ROOT_URL)
public class EmployeeDetailController {

    private EmployeeDetailService employeeDetailService;

    @Autowired
    public EmployeeDetailController(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }

    //1. get employee
    //2. get all employees
    //3. save employee details
    //4. update employee details
    //5. delete employee details

    @GetMapping(value = "/employee/{employeeId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEmployeeData(@PathVariable Long employeeId) throws Exception {
        log.info("Inside class -> EmployeeDetailController method -> getEmployeeData()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        final EmployeeDto employeeDetails = employeeDetailService.getEmployeeData(employeeId);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(employeeDetails);
    }

    @GetMapping(value = "/employees",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEmployeesData() {
        log.info("Inside class -> EmployeeDetailController method -> getAllEmployeesData()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        final List<EmployeeDto> employeeDetailsList = employeeDetailService.getAllEmployeeData();
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(employeeDetailsList);
    }

    @PostMapping(value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
        log.info("Inside class -> EmployeeDetailController method -> saveEmployeeDetails()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        final EmployeeDto resultEmployeeDto = employeeDetailService.saveEmployeeData(employeeDto);
        return new ResponseEntity<>(resultEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
        log.info("Inside class -> EmployeeDetailController method -> updateEmployeeDetails()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        final EmployeeDto resultEmployeeDto = employeeDetailService.updateEmployeeData(employeeDto);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(resultEmployeeDto);
    }

    @DeleteMapping(value = "/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployeeDetails(@PathVariable Long employeeId) {
        log.info("Inside class -> EmployeeDetailController method -> deleteEmployeeDetails()");

        HttpHeaders headers = new HttpHeaders();
        final String resultMessage = employeeDetailService.deleteEmployeeData(employeeId);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(resultMessage);
    }
}
