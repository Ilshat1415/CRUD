package ru.liga.crud.support;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.liga.crud.entity.Employee;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {
}
