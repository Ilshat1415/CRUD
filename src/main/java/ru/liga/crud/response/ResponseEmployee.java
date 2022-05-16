package ru.liga.crud.response;

import lombok.Getter;
import lombok.Setter;
import ru.liga.crud.entity.Employee;

@Getter
@Setter
public class ResponseEmployee {
    private String status;
    private String message;
    private Employee employee;
}
