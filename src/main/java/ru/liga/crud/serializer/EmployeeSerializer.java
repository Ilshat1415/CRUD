package ru.liga.crud.serializer;
//todo лучше переместить в пакет support

import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.liga.crud.entity.Employee;

public class EmployeeSerializer extends JsonSerializer<Employee> { //todo не используется ?
}
