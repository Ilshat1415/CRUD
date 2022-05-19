package ru.liga.crud.api;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id) throws ValidationException;

    void saveEmployee(Employee employee) throws ValidationException;

    Employee updateEmployee(Employee employee) throws ValidationException;

    void deleteEmployee(Long id) throws ValidationException;

}
