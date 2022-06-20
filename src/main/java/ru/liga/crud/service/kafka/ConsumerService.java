package ru.liga.crud.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.repository.EmployeeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final EmployeeRepository employeeRepository;

    @KafkaListener(topics = "${topic.add}")
    public void consume(ConsumerRecord<String, Employee> topic) {
        log.info("The consumer got topic: {}", topic.key());

        if (!employeeRepository.existsByUuid(topic.key())) {
            employeeRepository.save(topic.value());
            log.debug("Employee {} added to database", topic.value());
        }
    }
}
