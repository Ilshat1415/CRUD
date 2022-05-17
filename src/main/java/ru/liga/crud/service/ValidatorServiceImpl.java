package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.crud.api.ValidatorService;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;
import ru.liga.crud.type.Position;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {
    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Override
    public void validate(Employee employee) throws ValidationException {
        employeeChecker.checkEmployeeForNull(employee);
        employeeChecker.checkFirstName(employee);
        employeeChecker.checkLastName(employee);
        employeeChecker.checkPosition(employee);

        Position position = Position.getValue(employee);
        employeeChecker.checkSalary(employee, position);

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

        employeeChecker.checkTasks(employee, position);

        if (!employee.isValid()) {
            log.debug("Employee {} failed check", employee);

            throw new ValidationException();
        }

        log.debug("Employee {} passed check", employee);
    }
}
