package ru.liga.crud.interfaces;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.InvalidFieldException;
import ru.liga.crud.exception.NullEmployeeException;

public interface ValidatorService {

    void validate(Employee employee) throws InvalidFieldException, NullEmployeeException;
}
