package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByOffice_Id(String officeId);
    List<Employee> findByJobTitle(String jobTitle);
    List<Employee> findByReportsTo_Id(String managerId);
}