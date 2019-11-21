package com.crud.companyemployee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue
    @Column (name = "EMPLOYEE_ID")
    private Long id;

    @Column (name = "EMPLOYEE_NAME")
    private String name;

    @Column (name = "EMPLOYEE_DEPT")
    private String department;

}
