package ru.liga.crud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.liga.crud.entity.Employee;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEmployee {
    private String status;
    private String message;
    private Employee employee;
}
