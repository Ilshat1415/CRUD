package ru.liga.crud.service;

import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.PositionsEnum;

@Service
public class ValidatorService {
    public void validate(Employee employee) throws IllegalArgumentException {
        checkRequiredFields(employee);
        PositionsEnum position = PositionsEnum.getValue(employee.getPosition());
        checkSalary(position, employee.getSalary());

        switch (position) {
            case TESTER:
                checkTesterFields(employee);
                break;
            case DEVELOPER:
                checkDeveloperFields(employee);
                break;
            case TEAM_LEAD:
                checkTeamLeadFields(employee);
                break;
            case MANAGER:
                checkManagerFields(employee);
        }
    }

    public void checkId(Long id, Employee employee) throws IllegalArgumentException {
        if (employee == null) {
            throw new IllegalArgumentException(String.format("Id = %d not found", id));
        }
    }

    private void checkRequiredFields(Employee employee) throws IllegalArgumentException {
        if (employee.getFirstName() == null
                || employee.getLastName() == null
                || employee.getPosition() == null) {
            throw new IllegalArgumentException("Fields FirstName, LastName, Position are mandatory");
        }
    }

    private void checkSalary(PositionsEnum position, int salary) throws IllegalArgumentException {
        if (salary < position.getSalaryMin() || salary > position.getSalaryMax()) {
            throw new IllegalArgumentException(String.format(
                    "The %s must have a salary between %d and %d, requested: %d",
                    position.getPosition(),
                    position.getSalaryMin(),
                    position.getSalaryMax(),
                    salary
            ));
        }
    }

    private void checkTesterFields(Employee employee) throws IllegalArgumentException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() != null) {
            throw new IllegalArgumentException("For the tester position, fields ProgrammingLanguage, NumberOfSubordinates must be empty");
        }
    }

    private void checkDeveloperFields(Employee employee) throws IllegalArgumentException {
        if (employee.getNumberOfSubordinates() != null) {
            throw new IllegalArgumentException("For the developer position, field NumberOfSubordinates must be empty");
        }

        if (employee.getProgrammingLanguage() == null) {
            throw new IllegalArgumentException("For the developer position, field ProgrammingLanguage are mandatory");
        }
    }

    private void checkTeamLeadFields(Employee employee) throws IllegalArgumentException {
        if (employee.getProgrammingLanguage() != null) {
            throw new IllegalArgumentException("For the team lead position, field ProgrammingLanguage must be empty");
        }

        if (employee.getTelephoneNumber() == null || employee.getNumberOfSubordinates() == null) {
            throw new IllegalArgumentException("For the team lead position, fields TelephoneNumber, NumberOfSubordinates are mandatory");
        }
    }

    private void checkManagerFields(Employee employee) throws IllegalArgumentException {
        if (employee.getProgrammingLanguage() != null) {
            throw new IllegalArgumentException("For the developer position, field ProgrammingLanguage must be empty");
        }

        if (employee.getTelephoneNumber() == null || employee.getEmail() == null) {
            throw new IllegalArgumentException("For the developer position, fields TelephoneNumber, Email are mandatory");
        }
    }
}
