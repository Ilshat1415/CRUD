package ru.liga.crud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liga.crud.initializer.InitializerForTests;
import ru.liga.crud.entity.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeControllerTest extends InitializerForTests {
    private final EmployeeController employeeController;

    @Autowired
    public EmployeeControllerTest(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Test
    void addMultipleEmployee_Employees_True() {
        List<Employee> employees = new ArrayList<>();
        Collections.addAll(employees, new Employee(), new Employee(), new Employee());

        assertThat(employeeController.addEmployee(employees).getStatusCode().is2xxSuccessful()).isTrue();
    }
}