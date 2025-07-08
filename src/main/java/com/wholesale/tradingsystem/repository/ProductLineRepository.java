package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, String> {
}
