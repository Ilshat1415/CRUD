package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.exception.IdNotFoundException;
import ru.exception.InvalidFieldException;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ValidatorService validatorService = new ValidatorService();

    public Employee findById(Long id) throws IdNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        validatorService.checkId(id, employee);

        return employee;
    }

    public void saveEmployee(Employee employee) throws InvalidFieldException {
        validatorService.validate(employee);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) throws InvalidFieldException, IdNotFoundException {
        findById(employee.getId());
        saveEmployee(employee);
    }

    public void deleteEmployee(Long id) throws InvalidFieldException, IdNotFoundException {
        findById(id);
        employeeRepository.deleteById(id);
    }
}
