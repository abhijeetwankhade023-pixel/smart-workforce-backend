package com.smartworkforce.controller;


import com.smartworkforce.entity.Employee;
import com.smartworkforce.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee( @RequestBody Employee employee) {
       Employee savedEmployee = employeeService.saveEmployee(employee);
       return ResponseEntity.ok().body(savedEmployee);
    }
}
