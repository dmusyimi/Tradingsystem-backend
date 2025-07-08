package com.wholesale.tradingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private String status;
    private String comments;
    private Integer customerNumber;
    private List<OrderDetailDTO> orderDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailDTO {
        private String productCode;
        private Integer quantityOrdered;
        private Double priceEach;
        private Integer orderLineNumber;
    }
}
