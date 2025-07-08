package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer_CustomerNumber(Integer customerNumber);
    List<Order> findByStatus(String status);
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}
