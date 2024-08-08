package innovativebuddies.universecommons.patterns.observer.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Основной класс приложения, включающий поддержку асинхронного выполнения.
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }
}
