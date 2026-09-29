package com.smartworkforce.controller;


import com.smartworkforce.dto.EmployeeRequest;
import com.smartworkforce.dto.EmployeeResponse;
import com.smartworkforce.entity.Employee;
import com.smartworkforce.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Employee> saveEmployee(@Valid  @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartment(employeeRequest.getDepartment());

        employee.setEmail(employeeRequest.getEmail());
        employee.setSalary(employeeRequest.getSalary());
       Employee savedEmployee = employeeService.saveEmployee(employee);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployeeResponses();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {

        EmployeeResponse employeeResponse = employeeService.getEmployeeResponseById(id);


        return ResponseEntity.ok(employeeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest employeeRequest) {

        EmployeeResponse updatedEmployee =
                employeeService.updateEmployeeResponse(id, employeeRequest);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

       employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee deleted Successfully");
    }
}
