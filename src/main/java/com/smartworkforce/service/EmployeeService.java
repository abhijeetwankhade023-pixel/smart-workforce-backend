package com.smartworkforce.service;


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

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
    }

    public Employee  updateEmployee( Long id ,Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee with id " + id + " not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
         return employeeRepository.save(existingEmployee);

    }

    public boolean deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));

        employeeRepository.delete(employee);
        return true;
    }
}
