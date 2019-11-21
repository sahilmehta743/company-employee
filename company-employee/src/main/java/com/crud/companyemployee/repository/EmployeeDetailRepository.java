package com.crud.companyemployee.repository;

import com.crud.companyemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<Employee, Long> {
}
