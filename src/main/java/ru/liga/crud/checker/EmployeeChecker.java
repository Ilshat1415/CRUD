package ru.liga.crud.checker;

import lombok.RequiredArgsConstructor;
import ru.liga.crud.entity.Employee;
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

    public void checkRequiredFields(Employee employee) throws ValidationException {
        if (employee.getFirstName() == null
                || employee.getLastName() == null
                || employee.getPosition() == null) {
            throw new ValidationException(resourceBundleService.getMessage("invalidRequiredFields"));
        }
    }

    public void checkSalary(Position position, int salary) throws ValidationException {
        if (salary < position.getSalaryMin() || salary > position.getSalaryMax()) {
            throw new ValidationException(String.format(
                    resourceBundleService.getMessage("invalidSalary"),
                    position.getPosition(),
                    position.getSalaryMin(),
                    position.getSalaryMax(),
                    salary));
        }
    }

    public void checkTesterFields(Employee employee) throws ValidationException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() != null) {
            throw new ValidationException(resourceBundleService.getMessage("invalidTesterFields"));
        }
    }

    public void checkDeveloperFields(Employee employee) throws ValidationException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() == null) {
            throw new ValidationException(resourceBundleService.getMessage("invalidDeveloperFields"));
        }
    }

    public void checkTeamLeadFields(Employee employee) throws ValidationException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getNumberOfSubordinates() == null) {
            throw new ValidationException(resourceBundleService.getMessage("invalidTeamLeadFields"));
        }
    }

    public void checkManagerFields(Employee employee) throws ValidationException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getEmail() == null) {
            throw new ValidationException(resourceBundleService.getMessage("invalidManagerFields"));
        }
    }
}
