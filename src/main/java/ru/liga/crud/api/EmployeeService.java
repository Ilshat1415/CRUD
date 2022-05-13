package ru.liga.crud.api;
//todo не оч название пакет. Множественное число. назови api
// done
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;

public interface EmployeeService {
    Employee findById(Long id) throws ValidationException;

    void saveEmployee(Employee employee) throws ValidationException;

    void updateEmployee(Employee employee) throws ValidationException;

    void deleteEmployee(Long id) throws ValidationException;
}
