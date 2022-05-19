package ru.liga.crud.serializer;
//todo лучше переместить в пакет support + не логично в пакет называется serializer, а в нем лежит Deserializer
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.liga.crud.entity.Employee;

public class EmployeeDeserializer extends JsonDeserializer<Employee> { //todo не используется ?
}
