package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.type.Position;

@RequiredArgsConstructor
public class ValidatorService {
    private final EmployeeChecker employeeChecker;

    public void validate(Employee employee) throws InvalidFieldException {
        employeeChecker.checkEmployeeForNull(employee);

        employeeChecker.checkRequiredFields(employee);
        Position position = Position.getValue(employee.getPosition());
        employeeChecker.checkSalary(position, employee.getSalary());

        switch (position) {
            case TESTER:
                employeeChecker.checkTesterFields(employee);
                break;
            case DEVELOPER:
                employeeChecker.checkDeveloperFields(employee);
                break;
            case TEAM_LEAD:
                employeeChecker.checkTeamLeadFields(employee);
                break;
            case MANAGER:
                employeeChecker.checkManagerFields(employee);
        }
    }
}
