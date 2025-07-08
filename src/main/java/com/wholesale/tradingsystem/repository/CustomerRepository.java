package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCountry(String country);
    List<Customer> findBySalesRepEmployee_EmployeeNumber(Integer employeeNumber);
}
