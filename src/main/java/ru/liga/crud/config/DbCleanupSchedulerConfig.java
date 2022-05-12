package ru.liga.crud.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.liga.crud.repository.EmployeeRepository;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DbCleanupSchedulerConfig {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${task-processor.cleanup.cron-expression}")
    public void cleanupRun() {
        employeeRepository.deleteOldId();
    }
}
