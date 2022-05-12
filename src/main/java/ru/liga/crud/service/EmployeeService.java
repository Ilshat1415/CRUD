package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ValidatorService validatorService;

    public Employee findById(Long id) throws IllegalArgumentException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        validatorService.checkId(id, employee);

        return employee;
    }

    public void saveEmployee(Employee employee) throws IllegalArgumentException {
        validatorService.validate(employee);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) throws IllegalArgumentException {
        findById(employee.getId());
        saveEmployee(employee);
    }

    public void deleteEmployee(Long id) throws IllegalArgumentException {
        findById(id);
        employeeRepository.deleteById(id);
    }
}
