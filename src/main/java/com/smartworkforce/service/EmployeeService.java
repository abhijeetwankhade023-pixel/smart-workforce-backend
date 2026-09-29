package com.smartworkforce.service;


import com.smartworkforce.dto.EmployeeRequest;
import com.smartworkforce.dto.EmployeeResponse;
import com.smartworkforce.entity.Employee;
import com.smartworkforce.exception.EmployeeNotFoundException;
import com.smartworkforce.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getAllEmployeeResponses() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> new EmployeeResponse(employee.getId(),
                employee.getName(), employee.getEmail(),employee.getDepartment(),employee.getSalary())).toList();
    }

    public EmployeeResponse getEmployeeResponseById(Long id) {
     Employee employee= employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException
                ("Employee with id " + id + " not found"));

        return new EmployeeResponse(employee.getId(),employee.getName(),employee.getEmail(),
                employee.getDepartment(),employee.getSalary());
    }

    public EmployeeResponse updateEmployeeResponse(
            Long id,
            EmployeeRequest employeeRequest) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with id " + id + " not found"
                        )
                );

        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getEmail(),
                updatedEmployee.getDepartment(),
                updatedEmployee.getSalary()
        );
    }

    public boolean deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));

        employeeRepository.delete(employee);
        return true;
    }
}
