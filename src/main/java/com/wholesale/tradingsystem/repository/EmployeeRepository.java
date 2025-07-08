package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByOffice_OfficeCode(String officeCode);
    List<Employee> findByJobTitle(String jobTitle);
    List<Employee> findByReportsTo_EmployeeNumber(Integer employeeNumber);
}
