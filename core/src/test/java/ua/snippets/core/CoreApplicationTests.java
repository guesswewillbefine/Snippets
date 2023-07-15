package ua.snippets.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
class CoreApplicationTests {
    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5")
            .withExposedPorts(3306);

    @DynamicPropertySource
    public static void setup(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);

        mySQLContainer.start();
    }

    @Test
    void contextLoads() {
    }

}
