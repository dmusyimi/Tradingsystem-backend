package com.wholesale.tradingsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer customerNumber;

    @NotBlank
    private String customerName;

    @NotBlank
    private String contactFirstName;

    @NotBlank
    private String contactLastName;

    @NotBlank
    private String phone;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String city;

    private String state;

    private String postalCode;

    @NotBlank
    private String country;

    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employee salesRepEmployee;

    private BigDecimal creditLimit;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments = new ArrayList<>();
}
