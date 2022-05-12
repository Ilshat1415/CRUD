package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService { //todo добавить интерфейс и ис пользователь через интерфейс
    private final EmployeeRepository employeeRepository;
    private final EmployeeChecker employeeChecker;
    private final ValidatorService validatorService;

    public Employee findById(Long id) throws InvalidFieldException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employeeChecker.checkId(id, employee);

        return employee;
    }

    public void saveEmployee(Employee employee) throws InvalidFieldException {
        validatorService.validate(employee);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) throws InvalidFieldException {
        findById(employee.getId());
        saveEmployee(employee);
    }

    public void deleteEmployee(Long id) throws InvalidFieldException {
        findById(id);
        employeeRepository.deleteById(id);
    }
}
