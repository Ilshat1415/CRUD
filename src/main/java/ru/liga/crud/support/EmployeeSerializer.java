package ru.liga.crud.support;

import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.liga.crud.entity.Employee;

public class EmployeeSerializer extends JsonSerializer<Employee> {

}
