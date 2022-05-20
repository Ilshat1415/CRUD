package ru.liga.crud.checker;

import lombok.RequiredArgsConstructor;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;
import ru.liga.crud.service.ResourceBundleService;
import ru.liga.crud.type.Position;

@RequiredArgsConstructor
public class EmployeeChecker {
    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();
    private static final int FIELD_LENGTH = 50;

    public void checkFirstName(Employee employee) {
        if (employee.getFirstName() == null) {
            employee.setFirstName(resourceBundleService.getMessage("fieldIsNull"));
            employee.isNotValid();

        } else if (employee.getFirstName().length() > FIELD_LENGTH) {
            employee.setFirstName(resourceBundleService.getMessage("invalidLength"));
            employee.isNotValid();
        }
    }

    public void checkLastName(Employee employee) {
        if (employee.getLastName() == null) {
            employee.setLastName(resourceBundleService.getMessage("fieldIsNull"));
            employee.isNotValid();

        } else if (employee.getLastName().length() > FIELD_LENGTH) {
            //todo волшебная цифра)) сделай константой
            // done
            employee.setLastName(resourceBundleService.getMessage("invalidLength"));
            employee.isNotValid();
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
                    employee.getSalary()));

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

    public void checkTasks(Employee employee) {
        for (Task task : employee.getTasks()) {
            task.setDescription(fieldIsNull(task.getDescription(), employee));
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
