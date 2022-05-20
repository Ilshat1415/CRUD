package ru.liga.crud.support;
//todo лучше переместить в пакет support
// done

import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.liga.crud.entity.Employee;

public class EmployeeSerializer extends JsonSerializer<Employee> {
    //todo не используется ?
    // done
}
