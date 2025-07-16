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

    public EmployeeDTO getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EmptyResultDataAccessException("Employee not found with id: " + id, 1));
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Transactional
    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) {
        if (!employeeRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Employee not found with id: " + id, 1);
        }

        Employee employee = convertToEntity(employeeDTO);
        employee.setId(id); // ensure we update the existing entity
        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDTO(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> getEmployeesByOffice(String officeId) {
        return employeeRepository.findByOffice_Id(officeId)
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

    public List<EmployeeDTO> getEmployeesByManager(String managerId) {
        return employeeRepository.findByReportsTo_Id(managerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setExtension(employee.getExtension());
        dto.setEmail(employee.getEmail());
        dto.setJobTitle(employee.getJobTitle());

        if (employee.getReportsTo() != null) {
            dto.setReportsToId(employee.getReportsTo().getId());
        }

        if (employee.getOffice() != null) {
            dto.setOfficeId(employee.getOffice().getId());
        }

        return dto;
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setExtension(dto.getExtension());
        entity.setEmail(dto.getEmail());
        entity.setJobTitle(dto.getJobTitle());

        // Set reporting relationship if provided
        if (dto.getReportsToId() != null) {
            Employee manager = employeeRepository.findById(dto.getReportsToId())
                    .orElseThrow(() -> new EmptyResultDataAccessException("Manager not found with id: " + dto.getReportsToId(), 1));
            entity.setReportsTo(manager);
        }

        // Set office if provided
        if (dto.getOfficeId() != null) {
            Office office = officeRepository.findById(dto.getOfficeId())
                    .orElseThrow(() -> new EmptyResultDataAccessException("Office not found with id: " + dto.getOfficeId(), 1));
            entity.setOffice(office);
        }

        return entity;
    }
}