package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.exception.NullEmployeeException;
import ru.liga.crud.interfaces.ValidatorService;
import ru.liga.crud.type.Position;

@Service
@RequiredArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {
    //todo добавить интерфейс и ис пользователь через интерфейс
    // done
    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    public void validate(Employee employee) throws InvalidFieldException, NullEmployeeException {
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
