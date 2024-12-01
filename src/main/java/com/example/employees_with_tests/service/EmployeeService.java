package com.example.employees_with_tests.service;

import com.example.employees_with_tests.model.Employee;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int department, int salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> getAll();


}
