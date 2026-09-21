package com.smartworkforce.service;


import com.smartworkforce.entity.Employee;
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
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee  updateEmployee( Long id ,Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return null;
        }
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
         return employeeRepository.save(existingEmployee);

    }

    public boolean deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return false;
        }
        employeeRepository.delete(employee);
        return true;
    }
}
