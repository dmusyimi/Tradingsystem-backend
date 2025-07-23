package com.wholesale.tradingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String productName;
    private String productLineId;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
}
