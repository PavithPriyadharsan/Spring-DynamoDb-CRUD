package com.springbootcrud.demo.controller;

import com.springbootcrud.demo.entity.Employee;
import com.springbootcrud.demo.repository.EmployeeRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('client_user')")    //User has access to get his details using ID
    public Employee getEmployee(@PathVariable("id") String id){
        return employeeRepository.getEmployeeById(id);
    }

    // Endpoint to get all employees
    @GetMapping("/employees")
    @PreAuthorize("hasRole('client_admin')")    //Admin has access to get details of all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public String deleteEmployee(@PathVariable("id") String id){
        return employeeRepository.delete(id);
    }

    @PutMapping("employee/{id}")
    @PreAuthorize("hasRole('client_user')")
    public String updateEmployee(@PathVariable("id") String id,@RequestBody Employee employee){
        return employeeRepository.update(id,employee);
    }

}
