package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.crud.api.ValidatorService;
import ru.liga.crud.checker.EmployeeChecker;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.type.Position;
import ru.liga.crud.type.Status;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {
    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();
    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Override
    public ResponseEmployee validate(Employee employee) {
        employeeChecker.checkFirstName(employee);
        employeeChecker.checkLastName(employee);
        employeeChecker.checkTasks(employee);

        Position position = Position.getValue(employee);
        String report = validateSpecializedFields(employee, position);

        if (!employee.isValid()) {
            log.debug("Employee {} failed verification and will not be added", employee);

            return ResponseEmployee.builder()
                    .status(Status.PROBLEM.name())
                    .message(report != null ? report : "Validate error")
                    .employee(employee)
                    .build();
        }

        log.debug("Employee {} passed check", employee);

        return ResponseEmployee.builder()
                .status(Status.SUCCESS.name())
                .message("Employee accepted")
                .employee(employee)
                .build();
    }

    private String validateSpecializedFields(Employee employee, Position position) {
        if (position == null) {
            employee.isNotValid();
            setUncheckedField(employee);

            return null;
        }

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

        if (employee.getTasks().size() > position.getNumberTasksMax()) {
            employee.isNotValid();

            return (String.format(
                    resourceBundleService.getMessage("invalidNumberTasks"),
                    position.getPosition(),
                    position.getNumberTasksMax(),
                    employee.getTasks().size()));
        } else {
            return null;
        }
    }

    private void setUncheckedField(Employee employee) {
        String uncheckedField = resourceBundleService.getMessage("uncheckedField");

        employee.setSalary(uncheckedField);
        employee.setProgrammingLanguage(uncheckedField);
        employee.setNumberOfSubordinates(uncheckedField);
        employee.setTelephoneNumber(uncheckedField);
        employee.setEmail(uncheckedField);


        for (Task task : employee.getTasks()) {
            task.setDescription(uncheckedField);
        }
    }
}
