package com.wholesale.tradingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineDTO {
    private String id;
    private String productLine;
    private String textDescription;
    private String htmlDescription;
}
