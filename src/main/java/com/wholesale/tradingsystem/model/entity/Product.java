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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String productCode;

    @NotBlank
    private String productName;

    @ManyToOne
    @JoinColumn(name = "productLine")
    private ProductLine productLine;

    private String productScale;

    private String productVendor;

    @Column(columnDefinition = "TEXT")
    private String productDescription;

    @NotNull
    private Integer quantityInStock;

    @NotNull
    private BigDecimal buyPrice;

    @NotNull
    private BigDecimal msrp; // Manufacturer's Suggested Retail Price

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
