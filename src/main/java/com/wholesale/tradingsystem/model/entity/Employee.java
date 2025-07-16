package com.wholesale.tradingsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

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
    @JoinColumn(name = "reportsToId")
    private Employee reportsTo;

    @OneToMany(mappedBy = "reportsTo")
    private List<Employee> subordinates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "officeId")
    private Office office;

    @OneToMany(mappedBy = "salesRepEmployee")
    private List<Customer> customers = new ArrayList<>();
}
