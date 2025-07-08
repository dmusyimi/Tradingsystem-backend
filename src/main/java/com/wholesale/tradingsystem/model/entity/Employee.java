package com.wholesale.tradingsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer employeeNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String extension;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employee reportsTo;

    @OneToMany(mappedBy = "reportsTo")
    private List<Employee> subordinates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Office office;

    @OneToMany(mappedBy = "salesRepEmployee")
    private List<Customer> customers = new ArrayList<>();
}
