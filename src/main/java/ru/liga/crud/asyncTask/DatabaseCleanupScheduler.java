package ru.liga.crud.asyncTask;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.liga.crud.repository.EmployeeRepository;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class DatabaseCleanupScheduler {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${task-processor.cleanup.cronExpression}")
    public void cleanupRun() {
        employeeRepository.deleteOldId();
    }
}
