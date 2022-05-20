package ru.liga.crud.api;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.response.ResponseEmployee;

public interface ValidatorService {

    ResponseEmployee validate(Employee employee);
}
