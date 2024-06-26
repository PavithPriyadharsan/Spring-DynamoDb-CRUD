package com.springbootcrud.demo.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.springbootcrud.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;
    @Autowired
    public EmployeeRepository (DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String id){
        return dynamoDBMapper.load(Employee.class, id);
    }

    // Method to find all employees
    public List<Employee> findAllEmployees() {
        return dynamoDBMapper.scan(Employee.class, new DynamoDBScanExpression());
    }

    public String delete(String id){
        Employee emp = dynamoDBMapper.load(Employee.class, id);
        dynamoDBMapper.delete(emp);
        return "Employee Deleted!";
    }

    public String update(String id, Employee employee){
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                ))
        );
        return id;
    }
}
