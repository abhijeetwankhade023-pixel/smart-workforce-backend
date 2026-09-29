package com.smartworkforce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Name is Required")
    private String name;

    @Email(message = "Email must be Valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department is Required")
    private String department;

    @Positive(message = "Salary must be greater than 0 )")
    private double salary;
}
