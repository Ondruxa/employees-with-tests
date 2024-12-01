package com.example.employees_with_tests.service;


import com.example.employees_with_tests.exception.EmployeeAlreadyAddedException;
import com.example.employees_with_tests.exception.EmployeeNotFoundException;
import com.example.employees_with_tests.exception.EmployeeStorageIsFullException;
import com.example.employees_with_tests.model.Employee;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private static final int SIZE_LIMIT = 5;
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(SIZE_LIMIT);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }


    public Employee add (Employee employee) {
        if (employees.size() >= SIZE_LIMIT) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee add(String firstName, String lastName, int department, int salary) {
        return null;
    }

    public Employee remove (String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));
    }

    public String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    public String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }
}
