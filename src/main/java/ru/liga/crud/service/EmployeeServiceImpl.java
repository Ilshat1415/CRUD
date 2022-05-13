package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.crud.api.EmployeeService;
import ru.liga.crud.api.ValidatorService;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;
import ru.liga.crud.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeChecker employeeChecker = new EmployeeChecker();
    private final EmployeeRepository employeeRepository;
    private final ValidatorService validatorService;

    public Employee findById(Long id) throws ValidationException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employeeChecker.checkId(id, employee);

        return employee;
    }

    public void saveEmployee(Employee employee) throws ValidationException {
        validatorService.validate(employee);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) throws ValidationException {
        findById(employee.getId());
        saveEmployee(employee);
    }

    public void deleteEmployee(Long id) throws ValidationException {
        findById(id);
        employeeRepository.deleteById(id);
    }
}
