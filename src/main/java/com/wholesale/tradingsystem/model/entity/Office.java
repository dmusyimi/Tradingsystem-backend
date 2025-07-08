package com.wholesale.tradingsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String officeCode;

    @NotBlank
    private String city;

    @NotBlank
    private String phone;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String territory;

    @OneToMany(mappedBy = "office")
    private List<Employee> employees = new ArrayList<>();
}
