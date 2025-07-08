package com.wholesale.tradingsystem.model.entity;

import jakarta.persistence.Column;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderNumber;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private LocalDate requiredDate;

    private LocalDate shippedDate;

    @NotBlank
    private String status;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "customerNumber")
    @NotNull
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
