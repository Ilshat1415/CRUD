package ru.liga.crud.service;

import ru.exception.IdNotFoundException;
import ru.exception.InvalidFieldException;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.PositionsEnum;

import java.util.ResourceBundle;

public class ValidatorService {
    //todo название файла "text" не очень. Лучше назвать по тому что содержит
    // + он используется в нескольких местах лучше вынести в отдельный класс по работе с Bundle
    private static final ResourceBundle rb = ResourceBundle.getBundle("text"); //todo лучше писать полное название переменной

    public void validate(Employee employee) throws InvalidFieldException {
        if (employee == null) { //todo вкусовщина, но я бы сделал отдельный приватный метод
            throw new IllegalArgumentException("Employee null");
        }

        checkRequiredFields(employee);
        PositionsEnum position = PositionsEnum.getValue(employee.getPosition());
        checkSalary(position, employee.getSalary());

        switch (position) {
            case TESTER:
                checkTesterFields(employee);
                break;
            case DEVELOPER:
                checkDeveloperFields(employee);
                break;
            case TEAM_LEAD:
                checkTeamLeadFields(employee);
                break;
            case MANAGER:
                checkManagerFields(employee);
        }
    }

    //todo вынеси чекеры в отдельный класс и в отдельный пакет checker
    public void checkId(Long id, Employee employee) throws IdNotFoundException {
        if (employee == null) {
            throw new IdNotFoundException(String.format(rb.getString("invalidId"), id));
        }
    }

    //todo не нравится что много текста в коде можно ли сообщения вынести ?
    // done
    private void checkRequiredFields(Employee employee) throws InvalidFieldException {
        if (employee.getFirstName() == null
                || employee.getLastName() == null
                || employee.getPosition() == null) {
            throw new InvalidFieldException(rb.getString("invalidRequiredFields"));
        }
    }

    private void checkSalary(PositionsEnum position, int salary) throws InvalidFieldException {
        if (salary < position.getSalaryMin() || salary > position.getSalaryMax()) {
            throw new InvalidFieldException(String.format(
                    rb.getString("invalidSalary"),
                    position.getPosition(),
                    position.getSalaryMin(),
                    position.getSalaryMax(),
                    salary
            ));
        }
    }

    private void checkTesterFields(Employee employee) throws InvalidFieldException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() != null) {
            throw new InvalidFieldException(rb.getString("invalidTesterFields"));
        }
    }

    private void checkDeveloperFields(Employee employee) throws InvalidFieldException {
        if (employee.getNumberOfSubordinates() != null || employee.getProgrammingLanguage() == null) {
            throw new InvalidFieldException(rb.getString("invalidDeveloperFields"));
        }
    }

    private void checkTeamLeadFields(Employee employee) throws InvalidFieldException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getNumberOfSubordinates() == null) {
            throw new InvalidFieldException(rb.getString("invalidTeamLeadFields"));
        }
    }

    private void checkManagerFields(Employee employee) throws InvalidFieldException {
        if (employee.getProgrammingLanguage() != null
                || employee.getTelephoneNumber() == null
                || employee.getEmail() == null) {
            throw new InvalidFieldException(rb.getString("invalidManagerFields"));
        }
    }
}
