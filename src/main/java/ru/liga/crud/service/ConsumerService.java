package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.KafkaTask;
import ru.liga.crud.repository.EmployeeRepository;
import ru.liga.crud.repository.KafkaTaskRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final EmployeeRepository employeeRepository;
    private final KafkaTaskRepository kafkaTaskRepository;

    @KafkaListener(topics = "${topic.add}")
    public void consume(ConsumerRecord<String, Employee> topic) {
        log.info("The consumer got topic: {}", topic);

        if (!kafkaTaskRepository.existsByUuid(topic.key())) {
            kafkaTaskRepository.save(new KafkaTask(topic.key()));
            employeeRepository.save(topic.value());
        }

        log.debug("Employee {} added to database", topic.value());
    }
}
