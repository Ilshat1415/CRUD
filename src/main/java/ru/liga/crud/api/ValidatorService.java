package ru.liga.crud.api;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;

public interface ValidatorService {

    void validate(Employee employee) throws ValidationException;
}
