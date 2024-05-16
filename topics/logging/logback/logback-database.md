# Логирование в базу данных в Logback

## Введение

> Logback поддерживает логирование в базу данных, что позволяет сохранять лог-сообщения в реляционной базе данных для последующего анализа и отчетности.
> Логирование в базу данных может быть полезно для централизованного хранения логов из нескольких приложений или служб.

## Как это работает

> Logback предоставляет `DBAppender`, который может записывать лог-сообщения в базу данных.
> `DBAppender` использует JDBC для подключения к базе данных и выполнения SQL-запросов для вставки логов в таблицу.

### Шаги для настройки логирования в базу данных

1. Настройте базу данных и создайте таблицу для логов:
   * Определите структуру таблицы для хранения логов.

2. Определите DBAppender в конфигурации Logback:
   * Настройте подключение к базе данных и параметры вставки.

3. Свяжите DBAppender с root-логгером или конкретными логгерами:
   * Используйте элемент <appender-ref ref="DB" /> для связывания DBAppender с логгерами.

#### Создание таблицы для логов

```sql
CREATE TABLE logging_event
(
   timestamp         BIGINT       NOT NULL,
   formatted_message TEXT         NOT NULL,
   logger_name       VARCHAR(254) NOT NULL,
   level_string      VARCHAR(254) NOT NULL,
   thread_name       VARCHAR(254),
   reference_flag    SMALLINT,
   arg0              VARCHAR(254),
   arg1              VARCHAR(254),
   arg2              VARCHAR(254),
   arg3              VARCHAR(254),
   caller_filename   VARCHAR(254),
   caller_class      VARCHAR(254),
   caller_method     VARCHAR(254),
   caller_line       CHAR(4),
   event_id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY
);
```

#### Примеры использования

```xml

<configuration>

   <!-- JDBC Appender для логирования в базу данных -->
   <appender name="DB" class="ch.qos.logback.classic.db.DBAppender">
      <connectionSource class="ch.qos.logback.core.db.DriverManagerConnectionSource">
         <driverClass>org.h2.Driver</driverClass>
         <url>jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1</url>
         <user>sa</user>
         <password></password>
      </connectionSource>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="DB"/>
   </root>

</configuration>
```

### Заключение

> Логирование в базу данных с помощью Logback позволяет централизованно хранить лог-сообщения для последующего анализа и отчетности.
> Следуя приведенным шагам и примерам, вы можете настроить DBAppender для логирования в базу данных в вашем приложении.
> Это дает вам мощный инструмент для управления логами и мониторинга состояния вашего приложения.

---

# Логирование в базу данных в Logback

#### Создание таблицы для логов

```postgresql
CREATE TABLE logging_event
(
   event_id          SERIAL PRIMARY KEY,
   timestmp          BIGINT       NOT NULL,
   formatted_message TEXT         NOT NULL,
   logger_name       VARCHAR(255) NOT NULL,
   level_string      VARCHAR(50)  NOT NULL,
   thread_name       VARCHAR(255),
   reference_flag    SMALLINT,
   arg0              VARCHAR(255),
   arg1              VARCHAR(255),
   arg2              VARCHAR(255),
   arg3              VARCHAR(255),
   caller_filename   VARCHAR(255),
   caller_class      VARCHAR(255),
   caller_method     VARCHAR(255),
   caller_line       VARCHAR(4)
);

CREATE TABLE logging_event_property
(
   event_id     BIGINT       NOT NULL,
   mapped_key   VARCHAR(255) NOT NULL,
   mapped_value TEXT,
   PRIMARY KEY (event_id, mapped_key),
   FOREIGN KEY (event_id) REFERENCES logging_event (event_id)
);

CREATE TABLE logging_event_exception
(
   event_id   BIGINT   NOT NULL,
   i          SMALLINT NOT NULL,
   trace_line TEXT,
   PRIMARY KEY (event_id, i),
   FOREIGN KEY (event_id) REFERENCES logging_event (event_id)
);
```

> Использование `timestmp` вместо `timestamp` в схеме базы данных для таблицы логов, создаваемой Logback, связано с наследием и внутренними требованиями Logback.
> Logback исторически использует имя `timestmp` для столбца, который хранит время лог-сообщения, чтобы избежать конфликтов с ключевым словом `timestamp`, которое зарезервировано в
> некоторых базах данных.

#### Пример конфигурации Logback для PostgreSQL

```xml

<configuration>

   <!-- JDBC Appender для логирования в базу данных PostgreSQL -->
   <appender name="DB" class="ch.qos.logback.classic.db.DBAppender">
      <connectionSource class="ch.qos.logback.core.db.DriverManagerConnectionSource">
         <driverClass>org.postgresql.Driver</driverClass>
         <url>jdbc:postgresql://localhost:5432/your_database</url>
         <user>your_username</user>
         <password>your_password</password>
      </connectionSource>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="DB"/>
   </root>

</configuration>
```

### Пример Java-кода с логированием

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgreSQLLoggingExample {

   private static final Logger logger = LoggerFactory.getLogger(PostgreSQLLoggingExample.class);

   public static void main(String[] args) {
      logger.info("This is an info message.");
      logger.warn("This is a warning message.");
      logger.error("This is an error message.");
   }
}
```

### Заключение

> Логирование в базу данных PostgreSQL с помощью Logback предоставляет мощные возможности для централизованного хранения лог-сообщений и последующего анализа.
> Используя тонкие настройки, вы можете настроить DBAppender для логирования в PostgreSQL, учитывая специфические потребности вашего приложения.
> Следуя приведенным примерам и рекомендациям, вы сможете эффективно настроить логирование в базу данных для вашего проекта.

