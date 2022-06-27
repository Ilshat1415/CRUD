package ru.liga.crud.initializer;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ContextConfiguration(initializers = InitializerForTest.class)
@EnableDiscoveryClient(autoRegister = false)
public class InitializerForTest
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:14.3");
    public static final KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                        "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                        "spring.datasource.username=" + postgresContainer.getUsername(),
                        "spring.datasource.password=" + postgresContainer.getPassword(),
                        "spring.kafka.consumer.bootstrap-servers="
                                + kafkaContainer.getBootstrapServers(),
                        "spring.kafka.producer.bootstrap-servers="
                                + kafkaContainer.getBootstrapServers())
                .applyTo(applicationContext);
    }

    @BeforeAll
    static void init() {
        postgresContainer.start();
        kafkaContainer.start();
    }
}
