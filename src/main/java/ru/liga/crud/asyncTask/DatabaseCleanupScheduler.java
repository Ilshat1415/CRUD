package ru.liga.crud.asyncTask;
//todo почему asynс ? обычно так называют когда есть асихроншина. Лучше перенести в config
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.liga.crud.repository.EmployeeRepository;

@Component //todo для Scheduling сделать @Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DatabaseCleanupScheduler {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${task-processor.cleanup.cronExpression}")
    public void cleanupRun() {
        employeeRepository.deleteOldId();
    }
}
