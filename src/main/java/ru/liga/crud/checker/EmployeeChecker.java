package ru.liga.crud.checker;

import lombok.RequiredArgsConstructor;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.exception.NullEmployeeException;
import ru.liga.crud.service.ResourceBundleService;
import ru.liga.crud.type.Position;

//todo Можно сделать не бином
// done
@RequiredArgsConstructor
public class EmployeeChecker {
    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();

    public void checkEmployeeForNull(Employee employee) throws NullEmployeeException {
        if (employee == null) {
            throw new NullEmployeeException(resourceBundleService.getMessage("invalidEmployee"));
            //todo сделай свой Exception + текст в Bundle
            // done
        }
    }

    public void checkId(Long id, Employee employee) throws InvalidFieldException {
        if (employee == null) {
            throw new InvalidFieldException(String.format(
                    resourceBundleService.getMessage("invalidId"),
                    id
            ));
        }
    }

    public void checkRequiredFields(Employee employee) throws InvalidFieldException {
        if (employee.getFirstName() == null
                || employee.getLastName() == null
                || employee.getPosition() == null) {
            throw new InvalidFieldException(resourceBundleService.getMessage("invalidRequiredFields"));
        }
    }

    public void checkSalary(Position position, int salary) throws InvalidFieldException {
        if (salary < position.getSalaryMin() || salary > position.getSalaryMax()) {
            throw new InvalidFieldException(String.format(
                    resourceBundleService.getMessage("invalidSalary"),
                    position.getPosition(),
                    position.getSalaryMin(),
                    position.getSalaryMax(),
                    salary
            ));
        }
    }

    public void checkTesterFields(Employee employee) throws InvalidFieldException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() != null) {
            throw new InvalidFieldException(resourceBundleService.getMessage("invalidTesterFields"));
        }
    }

    public void checkDeveloperFields(Employee employee) throws InvalidFieldException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() == null) {
            throw new InvalidFieldException(resourceBundleService.getMessage("invalidDeveloperFields"));
        }
    }

    public void checkTeamLeadFields(Employee employee) throws InvalidFieldException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getNumberOfSubordinates() == null) {
            throw new InvalidFieldException(resourceBundleService.getMessage("invalidTeamLeadFields"));
        }
    }

    public void checkManagerFields(Employee employee) throws InvalidFieldException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getEmail() == null) {
            throw new InvalidFieldException(resourceBundleService.getMessage("invalidManagerFields"));
        }
    }
}
