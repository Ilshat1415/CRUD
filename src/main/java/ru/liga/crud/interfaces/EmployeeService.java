package ru.liga.crud.interfaces;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.exception.NullEmployeeException;

public interface EmployeeService {
    Employee findById(Long id) throws InvalidFieldException;

    void saveEmployee(Employee employee) throws InvalidFieldException, NullEmployeeException;

    void updateEmployee(Employee employee) throws InvalidFieldException, NullEmployeeException;

    void deleteEmployee(Long id) throws InvalidFieldException;
}
