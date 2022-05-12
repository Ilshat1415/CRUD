package ru.liga.crud.checker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.service.ResourceBundleService;
import ru.liga.crud.type.Position;

@Component
@RequiredArgsConstructor
public class EmployeeChecker {
    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();
    //todo лучше писать полное название переменной
    // done

    public void checkEmployeeForNull(Employee employee) {
        if (employee == null) {
            //todo вкусовщина, но я бы сделал отдельный приватный метод
            // done
            throw new IllegalArgumentException("Employee null");
        }
    }

    //todo вынеси чекеры в отдельный класс и в отдельный пакет checker
    // done
    public void checkId(Long id, Employee employee) throws InvalidFieldException {
        if (employee == null) {
            throw new InvalidFieldException(String.format(
                    resourceBundleService.getStringMessageByKey("invalidId"),
                    id
            ));
        }
    }

    //todo не нравится что много текста в коде можно ли сообщения вынести ?
    // done
    public void checkRequiredFields(Employee employee) throws InvalidFieldException {
        if (employee.getFirstName() == null
                || employee.getLastName() == null
                || employee.getPosition() == null) {
            throw new InvalidFieldException(resourceBundleService.getStringMessageByKey("invalidRequiredFields"));
        }
    }

    public void checkSalary(Position position, int salary) throws InvalidFieldException {
        if (salary < position.getSalaryMin() || salary > position.getSalaryMax()) {
            throw new InvalidFieldException(String.format(
                    resourceBundleService.getStringMessageByKey("invalidSalary"),
                    position.getPosition(),
                    position.getSalaryMin(),
                    position.getSalaryMax(),
                    salary
            ));
        }
    }

    public void checkTesterFields(Employee employee) throws InvalidFieldException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() != null) {
            throw new InvalidFieldException(resourceBundleService.getStringMessageByKey("invalidTesterFields"));
        }
    }

    public void checkDeveloperFields(Employee employee) throws InvalidFieldException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() == null) {
            throw new InvalidFieldException(resourceBundleService.getStringMessageByKey("invalidDeveloperFields"));
        }
    }

    public void checkTeamLeadFields(Employee employee) throws InvalidFieldException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getNumberOfSubordinates() == null) {
            throw new InvalidFieldException(resourceBundleService.getStringMessageByKey("invalidTeamLeadFields"));
        }
    }

    public void checkManagerFields(Employee employee) throws InvalidFieldException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getEmail() == null) {
            throw new InvalidFieldException(resourceBundleService.getStringMessageByKey("invalidManagerFields"));
        }
    }
}
