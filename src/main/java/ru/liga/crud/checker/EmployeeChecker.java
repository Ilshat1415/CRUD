package ru.liga.crud.checker;

import lombok.RequiredArgsConstructor;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;
import ru.liga.crud.exception.ValidationException;
import ru.liga.crud.service.ResourceBundleService;
import ru.liga.crud.type.Position;

@RequiredArgsConstructor
public class EmployeeChecker {
    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();

    public void checkEmployeeForNull(Employee employee) throws ValidationException {
        if (employee == null) {
            throw new ValidationException(resourceBundleService.getMessage("invalidEmployee"));
        }
    }

    public void checkId(Long id, Employee employee) throws ValidationException {
        if (employee == null) {
            throw new ValidationException(String.format(resourceBundleService.getMessage("invalidId"), id));
        }
    }

    public void checkFirstName(Employee employee) {
        if (employee.getFirstName() == null) {
            employee.setFirstName(resourceBundleService.getMessage("fieldIsNull"));
            employee.isNotValid();

        } else if (employee.getFirstName().length() > 50) {
            employee.setFirstName(resourceBundleService.getMessage("invalidLength"));
            employee.isNotValid();
        }
    }

    public void checkLastName(Employee employee) {
        if (employee.getLastName() == null) {
            employee.setLastName(resourceBundleService.getMessage("fieldIsNull"));
            employee.isNotValid();

        } else if (employee.getLastName().length() > 50) {
            employee.setLastName(resourceBundleService.getMessage("invalidLength"));
            employee.isNotValid();
        }
    }

    public void checkPosition(Employee employee) throws ValidationException {
        if (employee.getPosition() == null) {
            employee.setPosition(resourceBundleService.getMessage("fieldIsNull"));

            throw new ValidationException();
        }
    }

    public void checkSalary(Employee employee, Position position) {
        try {
            int salary = Integer.parseInt(employee.getSalary());

            if (salary < position.getSalaryMin() || salary > position.getSalaryMax()) {
                employee.setSalary(String.format(
                        resourceBundleService.getMessage("invalidSalary"),
                        position.getPosition(),
                        position.getSalaryMin(),
                        position.getSalaryMax(),
                        salary));

                employee.isNotValid();
            }

        } catch (NumberFormatException e) {
            employee.setSalary(String.format(
                    resourceBundleService.getMessage("notNumber"),
                    employee.getSalary()
            ));

            employee.isNotValid();
        }
    }

    public void checkTesterFields(Employee employee) {
        employee.setNumberOfSubordinates(fieldIsNotNull(employee.getNumberOfSubordinates(), employee));
        employee.setProgrammingLanguage(fieldIsNotNull(employee.getProgrammingLanguage(), employee));
    }

    public void checkDeveloperFields(Employee employee) {
        employee.setNumberOfSubordinates(fieldIsNotNull(employee.getNumberOfSubordinates(), employee));
        employee.setProgrammingLanguage(fieldIsNull(employee.getProgrammingLanguage(), employee));
    }

    public void checkTeamLeadFields(Employee employee) {
        employee.setProgrammingLanguage(fieldIsNotNull(employee.getProgrammingLanguage(), employee));
        employee.setTelephoneNumber(fieldIsNull(employee.getTelephoneNumber(), employee));
        employee.setNumberOfSubordinates(fieldIsNull(employee.getNumberOfSubordinates(), employee));
    }

    public void checkManagerFields(Employee employee) {
        employee.setProgrammingLanguage(fieldIsNotNull(employee.getProgrammingLanguage(), employee));
        employee.setTelephoneNumber(fieldIsNull(employee.getTelephoneNumber(), employee));
        employee.setEmail(fieldIsNull(employee.getEmail(), employee));
    }

    public void checkTasks(Employee employee, Position position) throws ValidationException {
        for (Task task : employee.getTasks()) {
            task.setDescription(fieldIsNull(task.getDescription(), employee));
        }

        if (employee.getTasks().size() > position.getNumberTasksMax()) {
            throw new ValidationException(String.format(
                    resourceBundleService.getMessage("invalidNumberTasks"),
                    position.getPosition(),
                    position.getNumberTasksMax(),
                    employee.getTasks().size()));
        }
    }

    private String fieldIsNull(String value, Employee employee) {
        if (value == null) {
            employee.isNotValid();
            return resourceBundleService.getMessage("fieldIsNull");
        } else {
            return value;
        }
    }

    private String fieldIsNotNull(String value, Employee employee) {
        if (value != null) {
            employee.isNotValid();
            return resourceBundleService.getMessage("fieldIsNotNull");
        } else {
            return null;
        }
    }
}
