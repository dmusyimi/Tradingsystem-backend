package com.wholesale.tradingsystem.controller;

import com.wholesale.tradingsystem.dto.CustomerDTO;
import com.wholesale.tradingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer customerNumber) {
        return ResponseEntity.ok(customerService.getCustomerById(customerNumber));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        CustomerDTO created = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{customerNumber}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Integer customerNumber,
            @Validated @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(customerNumber, customerDTO));
    }

    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerNumber) {
        customerService.deleteCustomer(customerNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByCountry(@PathVariable String country) {
        return ResponseEntity.ok(customerService.getCustomersByCountry(country));
    }

    @GetMapping("/salesRep/{employeeNumber}")
    public ResponseEntity<List<CustomerDTO>> getCustomersBySalesRep(@PathVariable Integer employeeNumber) {
        return ResponseEntity.ok(customerService.getCustomersBySalesRep(employeeNumber));
    }
}
