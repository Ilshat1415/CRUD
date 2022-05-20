package ru.liga.crud.api;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.response.ResponseEmployee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    ResponseEmployee findByUuid(String uuid);

    ResponseEmployee saveEmployee(Employee employee);

    ResponseEmployee updateEmployee(Employee employee);

    ResponseEmployee deleteEmployee(String uuid);
}
