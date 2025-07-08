package com.wholesale.tradingsystem.service;

import com.wholesale.tradingsystem.dto.CustomerDTO;
import com.wholesale.tradingsystem.model.entity.Customer;
import com.wholesale.tradingsystem.model.entity.Employee;
import com.wholesale.tradingsystem.repository.CustomerRepository;
import com.wholesale.tradingsystem.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Integer customerNumber) {
        return customerRepository.findById(customerNumber)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EmptyResultDataAccessException("Customer not found with number: " + customerNumber, 1));
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    @Transactional
    public CustomerDTO updateCustomer(Integer customerNumber, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(customerNumber)) {
            throw new EntityNotFoundException("Customer not found with number: " + customerNumber);
        }

        Customer customer = convertToEntity(customerDTO);
        customer.setCustomerNumber(customerNumber); // ensure we update the existing entity
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDTO(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Integer customerNumber) {
        if (!customerRepository.existsById(customerNumber)) {
            throw new EntityNotFoundException("Customer not found with number: " + customerNumber);
        }
        customerRepository.deleteById(customerNumber);
    }

    public List<CustomerDTO> getCustomersByCountry(String country) {
        return customerRepository.findByCountry(country)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomersBySalesRep(Integer employeeNumber) {
        return customerRepository.findBySalesRepEmployee_EmployeeNumber(employeeNumber)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerNumber(customer.getCustomerNumber());
        dto.setCustomerName(customer.getCustomerName());
        dto.setContactFirstName(customer.getContactFirstName());
        dto.setContactLastName(customer.getContactLastName());
        dto.setPhone(customer.getPhone());
        dto.setAddressLine1(customer.getAddressLine1());
        dto.setAddressLine2(customer.getAddressLine2());
        dto.setCity(customer.getCity());
        dto.setState(customer.getState());
        dto.setPostalCode(customer.getPostalCode());
        dto.setCountry(customer.getCountry());

        if (customer.getSalesRepEmployee() != null) {
            dto.setSalesRepEmployeeNumber(customer.getSalesRepEmployee().getEmployeeNumber());
        }

        dto.setCreditLimit(customer.getCreditLimit());
        return dto;
    }

    private Customer convertToEntity(CustomerDTO dto) {
        Customer entity = new Customer();
        entity.setCustomerNumber(dto.getCustomerNumber());
        entity.setCustomerName(dto.getCustomerName());
        entity.setContactFirstName(dto.getContactFirstName());
        entity.setContactLastName(dto.getContactLastName());
        entity.setPhone(dto.getPhone());
        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPostalCode(dto.getPostalCode());
        entity.setCountry(dto.getCountry());

        // Set sales rep employee if provided
        if (dto.getSalesRepEmployeeNumber() != null) {
            Employee salesRep = employeeRepository.findById(dto.getSalesRepEmployeeNumber())
                    .orElseThrow(() -> new EmptyResultDataAccessException("Employee not found with number: " + dto.getSalesRepEmployeeNumber(), 1));
            entity.setSalesRepEmployee(salesRep);
        }

        entity.setCreditLimit(dto.getCreditLimit());
        return entity;
    }
}
