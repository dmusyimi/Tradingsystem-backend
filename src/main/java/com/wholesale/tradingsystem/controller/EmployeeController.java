package com.wholesale.tradingsystem.controller;

import com.wholesale.tradingsystem.dto.EmployeeDTO;
import com.wholesale.tradingsystem.service.EmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer employeeNumber) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeNumber));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Validated @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeNumber}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Integer employeeNumber,
            @Validated @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeNumber, employeeDTO));
    }

    @DeleteMapping("/{employeeNumber}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer employeeNumber) {
        employeeService.deleteEmployee(employeeNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/office/{officeCode}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByOffice(@PathVariable String officeCode) {
        return ResponseEntity.ok(employeeService.getEmployeesByOffice(officeCode));
    }

    @GetMapping("/job-title/{jobTitle}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByJobTitle(@PathVariable String jobTitle) {
        return ResponseEntity.ok(employeeService.getEmployeesByJobTitle(jobTitle));
    }

    @GetMapping("/manager/{managerEmployeeNumber}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByManager(@PathVariable Integer managerEmployeeNumber) {
        return ResponseEntity.ok(employeeService.getEmployeesByManager(managerEmployeeNumber));
    }
}
