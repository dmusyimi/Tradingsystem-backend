package com.wholesale.tradingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    private String jobTitle;
    private String reportsToId;
    private String officeId;
}
