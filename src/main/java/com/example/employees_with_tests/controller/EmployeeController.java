package com.example.employees_with_tests.controller;

import com.example.employees_with_tests.model.Employee;
import com.example.employees_with_tests.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(
            @RequestParam("firstName") String firstName,
            @RequestParam ("lastName") String lastName,
            @RequestParam ("department") int department,
            @RequestParam ("salary") int salary
    ){
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping ("/remove")
    public Employee remove(
            @RequestParam ("firstName") String firstName,
            @RequestParam ("lastName") String lastName
    ){
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping ("/find")
    public Employee find(
            @RequestParam ("firstName") String firstName,
            @RequestParam ("lastName") String lastName
    ){
        return employeeService.find(firstName, lastName);
    }


}

