# Логирование в файлы в Logback

## Введение

> Логирование в файлы является одной из наиболее часто используемых функций в Logback.
> Оно позволяет сохранять логи на диске для последующего анализа и архивирования.
> Logback поддерживает несколько типов файловых appenders, включая простой FileAppender и более сложный RollingFileAppender, который поддерживает ротацию файлов.

## Как это работает

> Для логирования в файлы в Logback необходимо определить FileAppender или RollingFileAppender в конфигурации Logback.
> Эти appenders могут быть настроены для записи логов в указанные файлы с различными параметрами, такими как шаблон форматирования, максимальный размер файла и политика ротации.

### Шаги для настройки логирования в файлы

1. Определите FileAppender или RollingFileAppender в конфигурации Logback:
   * Настройте путь к файлу, шаблон форматирования и другие параметры.

2. Свяжите appender с root-логгером или конкретными логгерами:
   * Используйте элемент <appender-ref ref="AppenderName" /> для связывания appender с логгерами.

#### Примеры использования

###### Пример с простым FileAppender

```xml

<configuration>

   <!-- Файловый Appender -->
   <appender name="FILE" class="ch.qos.logback.core.FileAppender">
      <file>logs/app.log</file>
      <append>true</append>
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="FILE"/>
   </root>

</configuration>
```

###### Пример с RollingFileAppender

```xml

<configuration>

   <!-- Rolling File Appender -->
   <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>logs/app.log</file>
      <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         <!-- Ротация файлов каждый день -->
         <fileNamePattern>logs/app.%d{yyyy-MM-dd}.log</fileNamePattern>
         <!-- Хранить логи за последние 30 дней -->
         <maxHistory>30</maxHistory>
      </rollingPolicy>
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="ROLLING"/>
   </root>

</configuration>
```

###### Пример с комбинированной ротацией (время и размер)

```xml

<configuration>

   <!-- Rolling File Appender с комбинированной ротацией (время и размер) -->
   <appender name="TIME_AND_SIZE_BASED" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>logs/time_and_size_based.log</file>
      <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         <fileNamePattern>logs/time_and_size_based.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
         <maxFileSize>10MB</maxFileSize>
         <maxHistory>30</maxHistory>
      </rollingPolicy>
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="TIME_AND_SIZE_BASED"/>
   </root>

</configuration>
```

### Логирование ошибок в отдельный файл

```xml

<configuration>

   <!-- Консольный Appender -->
   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Файловый Appender для ошибок -->
   <appender name="ERROR_FILE" class="ch.qos.logback.core.FileAppender">
      <file>logs/error.log</file>
      <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
         <level>ERROR</level>
      </filter>
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="STDOUT"/>
      <appender-ref ref="ERROR_FILE"/>
   </root>

</configuration>
```

### Заключение

> Логирование в файлы с помощью Logback позволяет сохранять логи для последующего анализа и архивирования.
> Используя FileAppender и RollingFileAppender, вы можете настроить логирование в файлы с различными параметрами и политиками ротации.
> Следуя приведенным примерам и шагам, вы можете легко настроить и использовать логирование в файлы для вашего приложения.
