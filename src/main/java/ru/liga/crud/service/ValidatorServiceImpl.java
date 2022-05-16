package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;
import ru.liga.crud.api.ValidatorService;
import ru.liga.crud.type.Position;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {

    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Override
    public void validate(Employee employee) throws ValidationException {
        employeeChecker.checkEmployeeForNull(employee);

        employeeChecker.checkRequiredFields(employee);

        Position position = Position.getValue(employee.getPosition());
        employeeChecker.checkSalary(position, employee.getSalary());
        employeeChecker.checkNumberTasks(position, employee.getTasks().size());

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

        log.debug("Employee {} passed check", employee);
    }
}
