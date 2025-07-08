package com.wholesale.tradingsystem.service;

import com.wholesale.tradingsystem.dto.EmployeeDTO;
import com.wholesale.tradingsystem.model.entity.Employee;
import com.wholesale.tradingsystem.model.entity.Office;
import com.wholesale.tradingsystem.repository.EmployeeRepository;
import com.wholesale.tradingsystem.repository.OfficeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, OfficeRepository officeRepository) {
        this.employeeRepository = employeeRepository;
        this.officeRepository = officeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer employeeNumber) {
        return employeeRepository.findById(employeeNumber)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EmptyResultDataAccessException("Employee not found with number: " + employeeNumber, 1));
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Transactional
    public EmployeeDTO updateEmployee(Integer employeeNumber, EmployeeDTO employeeDTO) {
        if (!employeeRepository.existsById(employeeNumber)) {
            throw new EmptyResultDataAccessException("Employee not found with number: " + employeeNumber, 1);
        }

        Employee employee = convertToEntity(employeeDTO);
        employee.setEmployeeNumber(employeeNumber); // ensure we update the existing entity
        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDTO(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(Integer employeeNumber) {
        if (!employeeRepository.existsById(employeeNumber)) {
            throw new EntityNotFoundException("Employee not found with number: " + employeeNumber);
        }
        employeeRepository.deleteById(employeeNumber);
    }

    public List<EmployeeDTO> getEmployeesByOffice(String officeCode) {
        return employeeRepository.findByOffice_OfficeCode(officeCode)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByJobTitle(String jobTitle) {
        return employeeRepository.findByJobTitle(jobTitle)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByManager(Integer managerEmployeeNumber) {
        return employeeRepository.findByReportsTo_EmployeeNumber(managerEmployeeNumber)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeNumber(employee.getEmployeeNumber());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setExtension(employee.getExtension());
        dto.setEmail(employee.getEmail());
        dto.setJobTitle(employee.getJobTitle());

        if (employee.getReportsTo() != null) {
            dto.setReportsTo(employee.getReportsTo().getEmployeeNumber());
        }

        if (employee.getOffice() != null) {
            dto.setOfficeCode(employee.getOffice().getOfficeCode());
        }

        return dto;
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setEmployeeNumber(dto.getEmployeeNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setExtension(dto.getExtension());
        entity.setEmail(dto.getEmail());
        entity.setJobTitle(dto.getJobTitle());

        // Set reporting relationship if provided
        if (dto.getReportsTo() != null) {
            Employee manager = employeeRepository.findById(dto.getReportsTo())
                    .orElseThrow(() -> new EmptyResultDataAccessException("Manager not found with number: " + dto.getReportsTo(), 1));
            entity.setReportsTo(manager);
        }

        // Set office if provided
        if (dto.getOfficeCode() != null) {
            Office office = officeRepository.findById(dto.getOfficeCode())
                    .orElseThrow(() -> new EmptyResultDataAccessException("Office not found with code: " + dto.getOfficeCode(), 1));
            entity.setOffice(office);
        }

        return entity;
    }
}
