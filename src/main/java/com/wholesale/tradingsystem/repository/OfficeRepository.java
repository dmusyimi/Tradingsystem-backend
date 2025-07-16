package com.wholesale.tradingsystem.repository;

import com.wholesale.tradingsystem.model.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, String> {
    List<Office> findByCountry(String country);
    List<Office> findByCity(String city);
}