package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductLine_ProductLine(String productLine);
}
