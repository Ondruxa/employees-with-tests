package com.example.employees_with_tests;

import com.example.employees_with_tests.exception.EmployeeNotFoundException;
import com.example.employees_with_tests.model.Employee;
import com.example.employees_with_tests.service.DepartmentService;
import com.example.employees_with_tests.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll())
                .thenReturn(List.of(
                        new Employee("Ivan", "Ivanov", 1, 100000),
                        new Employee("Roman", "Petrov", 2, 150000),
                        new Employee("Maria", "Sviridova", 1, 250000),
                        new Employee("Anna", "Ivanova", 2, 200000),
                        new Employee("Oleg", "Carpov", 1, 120000)
                ));
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeeSalarySum(1);
        assertEquals(470000, actual);
    }

    @Test
    void min() {
        //given
        //when
        double actual = departmentService.getEmpWithMinSalary(1);

        //then
        assertEquals(100000, actual);
    }

    @Test
    void min_negative() {
        //given
        //when
        //then
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmpWithMinSalary(3));
    }

    @Test
    void max() {
        //given
        //when
        double actual = departmentService.getEmpWithMaxSalary(2);
        //then
        assertEquals(200000, actual);
    }

    @Test
    void max_negative() {
        //given
        //when
        //then
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmpWithMaxSalary(3));
    }

    @Test
    void filter() {
        //given
        //when
        List<Employee> actual = departmentService.getAll(1);

        //then
        assertEquals(3, actual.size());
        actual.forEach(employee -> assertEquals(1, employee.getDepartment()));
    }

    @Test
    void filter_negative() {
        //given
        //when
        List<Employee> actual = departmentService.getAll(3);

        //then
        assertTrue(actual.isEmpty());

    }

    @Test
    void grouped() {
        //given
        //when
        Map<Integer, List<Employee>> actual = departmentService.getAll();

        //then
        assertEquals(2, actual.keySet().size());
        for (Map.Entry<Integer, List<Employee>> entry : actual.entrySet()) {
            Integer department = entry.getKey();
            List<Employee> employees = entry.getValue();
            employees.forEach(employee -> assertEquals(department, employee.getDepartment()));
        }
    }

    @Test
    void remove_in_department() {
        //given
        //when
        //then
        departmentService.fireAllInDepartment(1);
        verify(employeeService, times(3)).remove(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }

}
