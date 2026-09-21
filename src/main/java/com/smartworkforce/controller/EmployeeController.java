package com.smartworkforce.controller;


import com.smartworkforce.entity.Employee;
import com.smartworkforce.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id , @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if(updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

        boolean deleted = employeeService.deleteEmployee(id);
        if(!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Employee deleted Successfully");
    }
}
