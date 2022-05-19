package ru.liga.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.crud.entity.KafkaTask;

public interface KafkaTaskRepository extends JpaRepository<KafkaTask, Long> {

    boolean existsByUuid(String key);
}
