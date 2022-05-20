package ru.liga.crud.service.kafka;
//todo помести сервисы кафки в отдельный пакет внутри service
// done

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {
    private final KafkaTemplate<String, Employee> kafkaTemplate;
    @Value("${topic.add}")
    private String topicAdd;

    public void produce(Employee employee) {
        log.info("The produce got employee: {}", employee);

        kafkaTemplate.send(topicAdd, employee.getUuid(), employee);
        //todo погоди, а как отслеживать каждую отдельную таску в кафке, если тут рандом ?
        // done
        kafkaTemplate.flush();

        log.debug("Topic posted");
    }
}
