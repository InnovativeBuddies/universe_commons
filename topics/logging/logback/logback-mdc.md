# Использование MDC для контекстного логирования в Logback

## Введение

> MDC (Mapped Diagnostic Context) позволяет добавлять контекстную информацию к лог-сообщениям.
> Это полезно для включения дополнительной информации, такой как идентификатор пользователя, идентификатор транзакции или любой другой контекст, который может помочь при анализе
> логов.
> MDC хранит данные в виде пары ключ-значение и автоматически добавляет эту информацию к каждому лог-сообщению.

## Как это работает

> MDC позволяет установить контекстные данные, которые будут добавлены ко всем лог-сообщениям текущего потока.
> Эти данные можно получить и использовать в шаблоне форматирования логов с помощью спецификатора `%X{key}`.

### Шаги для использования MDC

1. Установите значения в MDC в вашем Java-коде:
   * Используйте MDC.put(key, value) для установки значений.
   * Убедитесь, что значения удаляются из MDC после использования (`MDC.remove(key)` или `MDC.clear()`).

2. Настройте шаблон форматирования в конфигурации Logback:
   * Используйте спецификатор `%X{key}` для включения значений из MDC в лог-сообщения.

3. Логируйте сообщения как обычно:
   * MDC автоматически добавит контекстные данные к каждому лог-сообщению.

#### Пример Java-кода с использованием MDC

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCExample {

   private static final Logger logger = LoggerFactory.getLogger(MDCExample.class);

   public static void main(String[] args) {
      // Установка контекстных данных
      MDC.put("userId", "user123");
      MDC.put("transactionId", "txn456");

      try {
         logger.info("This is a log message with user and transaction context.");
         // Симуляция выполнения задачи
         Thread.sleep(1000);
         logger.debug("Debugging information...");
      } catch (InterruptedException e) {
         logger.error("Task interrupted", e);
      } finally {
         // Очистка MDC
         MDC.clear();
      }
   }
}
```

#### Пример конфигурации Logback для использования MDC

```xml

<configuration>

   <!-- Консольный Appender -->
   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} [userId=%X{userId}, transactionId=%X{transactionId}] - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="STDOUT"/>
   </root>

</configuration>
```

#### Пример вывода логов с MDC

> 2023-05-16 12:34:56.789 [main] INFO com.example.MDCExample [userId=user123, transactionId=txn456] - This is a log message with user and transaction context.

> 2023-05-16 12:34:57.789 [main] DEBUG com.example.MDCExample [userId=user123, transactionId=txn456] - Debugging information...

### Заключение

> Использование MDC в Logback позволяет добавлять контекстную информацию к лог-сообщениям, что делает логи более информативными и удобными для анализа.
> Следуя приведенным шагам и примерам, вы можете настроить и использовать MDC для контекстного логирования в вашем приложении.
