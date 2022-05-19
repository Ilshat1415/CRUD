package ru.liga.crud.serializer;

import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.liga.crud.entity.Employee;

public class EmployeeSerializer extends JsonSerializer<Employee> {
}
